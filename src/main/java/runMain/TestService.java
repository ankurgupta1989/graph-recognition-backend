package runMain;

import com.fasterxml.jackson.databind.ObjectMapper;
import shapesAndRecognizers.Point;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ankurgupta on 3/15/15.
 */
public class TestService {

    private static String serviceUrl = "http://localhost:9080/add_shape";
    
    public static HttpURLConnection openConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type",
                "application/json");
        connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/28.0.1500.71 Chrome/28.0.1500.71 Safari/537.36");
        connection.setRequestMethod("POST");
        return connection;
    }
    
    public static void main(String[] argv) throws IOException {

        List<Point> points = new ArrayList<Point>();
        Scanner in = new Scanner(System.in);
        int numPoints = in.nextInt();
        for (int i = 0; i < numPoints; i++) {
            double x = in.nextInt();
            double y = in.nextInt();
            points.add(new Point(x, y, 0));
        }

        HttpURLConnection connection = openConnection(new URL(serviceUrl));
        
        ObjectMapper mapper = new ObjectMapper();
        Writer jsonWriter = new StringWriter();

        mapper.writeValue(jsonWriter, points);
        connection.setRequestMethod("POST");
        
        OutputStream os = connection.getOutputStream();
        os.write(jsonWriter.toString().getBytes());
        os.close();
        System.out.println(connection.getResponseCode());
    }
}
