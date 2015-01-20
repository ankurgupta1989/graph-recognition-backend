package service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import gesture.GesturePoint;
import gesture.Input;
import graph.Graph;
import graph_algorithms.BFS;
import recognizer.GraphRecognizerImplementation;
import recognizer.GraphRecognizerInterface;
import service.input.AddExternalLabelRequest;
import service.input.AddValueRequest;
import shape.BeautifiedShape;
import shape.Point;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by ankurgupta on 11/24/14.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecognizerResource {

    private GraphRecognizerInterface recognizer;

    public RecognizerResource() {
        recognizer = new GraphRecognizerImplementation();
    }

    @POST
    @Path("add_shape")
    public synchronized BeautifiedShape addShape(List<Point> pointList) {
        return recognizer.addShape(pointList);
    }

    @POST
    @Path("add_value")
    public synchronized void addValue(AddValueRequest request) {
        recognizer.addValue(request.getPoint(), request.getValue());
    }

    @POST
    @Path("add_external_label")
    public synchronized void addExternalLabel(AddExternalLabelRequest request) {
        recognizer.addExternalLabel(request.getPoint(), request.getExternalLabel());
    }

    @POST
    @Path("finish")
    public synchronized void finish() throws IOException {
        recognizer.finishDrawing();
        Graph G = recognizer.getGraph();
        FileOutputStream fileOut = new FileOutputStream("/tmp/graph.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(G);
        out.close();
        fileOut.close();
    }

    @POST
    @Path("run_bfs")
    public synchronized String runBfs(String source) {
        BFS bfs = new BFS();
        Map<String, String> map = new HashMap<String, String>();
        map.put("source", source);
        StringBuilder returnValue = new StringBuilder();
        bfs.run(recognizer.getGraph(), map, returnValue);
        return returnValue.toString();
    }

    @POST
    @Path("start")
    public synchronized void start() {
        recognizer = new GraphRecognizerImplementation();
    }

    @POST
    @Path("get_all_shapes")
    public synchronized List<BeautifiedShape> getAllShapes() {
        return recognizer.getAllShapes();
    }

    /**
     * For gesture recognition experiment.
     * @param input
     * @throws IOException
     */
    @POST
    @Path("store_points")
    public synchronized void storePoints(Input input) throws IOException {
        try {
            String filename = "/Users/ankurgupta/Desktop/AI-Project-Kaggle/Rubine/train.csv";
            FileWriter fw = new FileWriter(filename, true);
            for (GesturePoint point:input.getPointList()) {
                fw.write(point.toString() + ";");
            }
            fw.write(input.getOutputClass() + "\n");
            fw.close();

            saveImage(input);
        }
        catch (IOException e) {
            throw e;
        }
    }

    public void saveImage(Input input) throws IOException {
        String dirName = "/Users/ankurgupta/Desktop/AI-Project-Kaggle/Rubine/";
        int stIndex = input.getImage().indexOf(',');
        String cStr = input.getImage().substring(stIndex + 1);
        byte[] bytearray = Base64.decode(cStr);
        BufferedImage bI = ImageIO.read(new ByteArrayInputStream(bytearray));
        String outClass = input.getOutputClass();
        Random random = new Random();
        ImageIO.write(bI, "png", new File(dirName + outClass + "/", "image" + random.nextInt(1000000) + ".png"));
    }


    public Graph getGraph() {
        return recognizer.getGraph();
    }
}

