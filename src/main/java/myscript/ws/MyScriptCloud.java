package myscript.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import myscript.ws.api.Point;
import myscript.ws.api.Stroke;
import myscript.ws.api.hwr.HWRComponent;
import myscript.ws.api.hwr.HWRInput;
import myscript.ws.api.hwr.HWROutput;
import myscript.ws.api.shape.ShapeInput;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MyScriptCloud {
	protected static final String UTF_8 = "UTF-8";
	
	private String recognitionCloudURL;

	private List<Stroke> aggregator = new ArrayList<Stroke>();
	
	private final String apiKey;

	private RecognitionListener listener;

	private final ObjectMapper mapper = new ObjectMapper();

	public MyScriptCloud(final String recognitionCloudURL, final String apiKey) {
		this.recognitionCloudURL = recognitionCloudURL;
		this.apiKey = apiKey;
	}

	protected String getPostData(String hwrInput) {
		try {
			return URLEncoder.encode(hwrInput.toString(), UTF_8);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	protected String getHWROutput(String json) {

		Reader jsonReader = new StringReader(json);
		HWROutput output = null;
		try {
			output = mapper.readValue(jsonReader, HWROutput.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final int selectedCandidateIdx = output.getResult().getTextSegmentResult().getSelectedCandidateIdx();
		return output.getResult().getTextSegmentResult().getCandidates().get(selectedCandidateIdx).getLabel();
	}

	protected HttpURLConnection openConnection(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded; charset=" + UTF_8);
		connection.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/28.0.1500.71 Chrome/28.0.1500.71 Safari/537.36");
		connection.setRequestMethod("POST");
		return connection;
	}

    public String recognizeShape(Stroke[] strokes) throws IOException {
        
        final String input = getShapeInput(strokes);
        HttpURLConnection connection = openConnection(new URL(recognitionCloudURL));
        OutputStream output = connection.getOutputStream();

        output.write(String.format("apiKey=%s&shapeInput=", apiKey).getBytes(UTF_8));
        System.out.println("Shape Data posted : " + input);

        String postData = getPostData(input);
        System.out.println(postData);
        output.write(postData.getBytes(UTF_8));
        output.flush();
        output.close();

        int responseCode = connection.getResponseCode();
        InputStream responseStream = (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) ? connection.getErrorStream() : connection.getInputStream();

        String json = URLDecoder.decode(readResponse(responseStream), UTF_8);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Response OK : " + json);
            return json;
        } else {
            System.err.println("HTTP Error: " + responseCode + " "+ connection.getResponseMessage());
            return null;
        }
        
    }
    
	public String recognize(Stroke[] strokes) throws IOException {

		final String input = getHWRInput(strokes);

		HttpURLConnection connection = openConnection(new URL(recognitionCloudURL));
		OutputStream output = connection.getOutputStream();

		output.write(String.format("apiKey=%s&hwrInput=", apiKey).getBytes(UTF_8));
		
		System.out.println("Text Data posted : " + input);
		
		String postData = getPostData(input);
		output.write(postData.getBytes(UTF_8));
		output.flush();
		output.close();

		int responseCode = connection.getResponseCode();
		InputStream responseStream = (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) ? connection.getErrorStream() : connection.getInputStream();

		String json = URLDecoder.decode(readResponse(responseStream), UTF_8);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("Response OK : " + json);
			return getHWROutput(json);
		} else {
			System.err.println("HTTP Error: " + responseCode + " "+ connection.getResponseMessage());
			return null;
		}
	}

	private String getHWRInput(Stroke[] strokes) {
		HWRInput input = new HWRInput();

		for (int strokeIndex = 0; strokeIndex < strokes.length; strokeIndex++) {
			input.addComponent();
			for (final Point point : strokes[strokeIndex].getPoints())
				input.addComponentPoint(point.x, point.y);
		}

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		Writer jsonWriter = new StringWriter();
		try {
			mapper.writeValue(jsonWriter, input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonWriter.toString();
	}
    
    private String getShapeInput(Stroke[] strokes) {
        ShapeInput shapeInput = new ShapeInput();
        List<HWRComponent> components = new ArrayList<HWRComponent>();
        for (Stroke stroke : strokes) {
            HWRComponent component = new HWRComponent();
            for (final Point point : stroke.getPoints()) {
                List<Float> XList = component.getX();
                XList.add(point.x);
                component.setX(XList);

                List<Float> YList = component.getX();
                YList.add(point.y);
                component.setY(YList);
            }
            components.add(component);
        }
        shapeInput.setComponents(components);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        Writer jsonWriter = new StringWriter();
        try {
            mapper.writeValue(jsonWriter, shapeInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonWriter.toString();
    }

	public static String readResponse(InputStream responseStream)
			throws IOException {
		StringBuffer response = new StringBuffer();
		byte[] data = new byte[2048];
		while (true) {
			int length = responseStream.read(data);
			if (length > 0) {
				response.append(new String(data, 0, length, UTF_8));
			} else {
				break;
			}
		}

		return response.toString();
	}

	public String addStroke(Stroke[] strokes) {
//		aggregator.add(s);
//
//		Stroke[] strokes = aggregator.toArray(new Stroke[aggregator.size()]);
		String recognized = null;
		try {
			recognized = recognize(strokes);
		} catch (IOException e) {
			// keep recognized null so that notifyListeners will notify an error
			// status
			recognized = null;
		} finally {
			if (listener != null) {
				listener.recognitionResult(recognized);
			}
		}
        return recognized;
	}

    public void addShapeStroke(Stroke s) {
        aggregator.add(s);

        // TODO do it in a separate thread
        Stroke[] strokes = aggregator.toArray(new Stroke[aggregator.size()]);
        String recognized = null;
        try {
            recognized = recognizeShape(strokes);
        } catch (IOException e) {
            // keep recognized null so that notifyListeners will notify an error
            // status
            recognized = null;
        } finally {
            if (listener != null) {
                listener.recognitionResult(recognized);
            }
        }
    }

	public void setListener(RecognitionListener listener) {
		this.listener = listener;
	}
}