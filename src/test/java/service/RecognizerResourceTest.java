package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gesture.Input;
import graph.Edge;
import graph.Graph;
import graph.Node;
import graph_algorithms.BFS;
import org.junit.Test;
import service.input.AddExternalLabelRequest;
import service.input.AddValueRequest;
import shape.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class RecognizerResourceTest {

    @Test
    public void bfsTest() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("/tmp/graph.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Graph G = (Graph) in.readObject();
        in.close();
        fileIn.close();

        BFS bfs = new BFS();
        Map<String, String> map = new HashMap<String, String>();
        String source = "A";
        map.put("source", source);

        StringBuilder builder = new StringBuilder();
        bfs.run(G, map, builder);
        System.out.println(builder.toString());
    }

    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {

        Graph G = testService();

        FileOutputStream fileOut = new FileOutputStream("/tmp/graph.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(G);
        out.close();
        fileOut.close();

        FileInputStream fileIn = new FileInputStream("/tmp/graph.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Graph gNew = (Graph) in.readObject();
        in.close();
        fileIn.close();

    }

    public void testSaveImage() throws IOException {
        RecognizerResource resource = new RecognizerResource();
        String dirName = "/Users/ankurgupta/Desktop/AI-Project-Kaggle/Rubine/";
        BufferedReader bR = new BufferedReader(new FileReader(dirName + "test.txt"));
        String inputString = bR.readLine();
        System.out.println(inputString);
        ObjectMapper mapper = new ObjectMapper();
        Input input = mapper.readValue(inputString, Input.class);
        resource.saveImage(input);
    }


    public Graph testService() throws IOException {

        RecognizerResource resource = new RecognizerResource();

        // Add all the shapes.
        addCircles(resource);
        addLines(resource);
        addValuesForCircles(resource);
        addLabelsForCircles(resource);
        addLabelsForLines(resource);
        addValuesForLines(resource);

        resource.finish();

        Graph G = resource.getGraph();
        Map<Node, List<Edge>> graph = G.getGraph();
        assertEquals(3, graph.size());
        for (List<Edge> edgeList : graph.values()) {
            assertEquals(2, edgeList.size());
        }

        return G;
    }

    private void addLabelsForLines(RecognizerResource resource) {
        // For the horizontal line.
        AddExternalLabelRequest req = new AddExternalLabelRequest();
        req.setExternalLabel("E1");
        req.setPoint(new Point(2, 0));
        resource.addExternalLabel(req);

        // For the vertical line.
        req = new AddExternalLabelRequest();
        req.setExternalLabel("E2");
        req.setPoint(new Point(0, 2));
        resource.addExternalLabel(req);

        // For the diagonal line.
        req = new AddExternalLabelRequest();
        req.setExternalLabel("E3");
        req.setPoint(new Point(2, 2));
        resource.addExternalLabel(req);
    }

    private void addValuesForLines(RecognizerResource resource) {
        // For the horizontal line.
        AddValueRequest request = new AddValueRequest();
        request.setValue(2);
        request.setPoint(new Point(2, 0));
        resource.addValue(request);

        // For the vertical line.
        request = new AddValueRequest();
        request.setValue(1);
        request.setPoint(new Point(0, 2));
        resource.addValue(request);

        // For the diagonal.
        request = new AddValueRequest();
        request.setValue(4);
        request.setPoint(new Point(2, 2));
        resource.addValue(request);
    }

    private void addLabelsForCircles(RecognizerResource resource) {
        // For the center circle.
        AddExternalLabelRequest req = new AddExternalLabelRequest();
        req.setExternalLabel("A");
        req.setPoint(new Point(-1, 0));
        resource.addExternalLabel(req);

        // For the right circle.
        req = new AddExternalLabelRequest();
        req.setExternalLabel("B");
        req.setPoint(new Point(4, -1));
        resource.addExternalLabel(req);

        // For the upper circle.
        req = new AddExternalLabelRequest();
        req.setExternalLabel("C");
        req.setPoint(new Point(-1, 4));
        resource.addExternalLabel(req);
    }

    private void addValuesForCircles(RecognizerResource resource) {
        // For the center circle.
        AddValueRequest request = new AddValueRequest();
        request.setValue(2);
        request.setPoint(new Point(-1, 0));
        resource.addValue(request);

        // For the right circle.
        request = new AddValueRequest();
        request.setValue(3);
        request.setPoint(new Point(4, -1));
        resource.addValue(request);

        // For the upper circle.
        request = new AddValueRequest();
        request.setValue(4);
        request.setPoint(new Point(-1, 4));
        resource.addValue(request);
    }

    private void addLines(RecognizerResource resource) {
        List<Point> points;

        // Add the horizontal line.
        points = new ArrayList<Point>();
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        resource.addShape(points);

        // Add the vertical line.
        points = new ArrayList<Point>();
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(0, 3));
        resource.addShape(points);

        // Add the diagonal line.
        points = new ArrayList<Point>();
        points.add(new Point(0, 4));
        points.add(new Point(1, 3));
        points.add(new Point(2, 2));
        points.add(new Point(3, 1));
        points.add(new Point(4, 0));
        resource.addShape(points);
    }

    private void addCircles(RecognizerResource resource) {
        // Adding the center circle.
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));
        points.add(new Point(-1, 0));
        points.add(new Point(0, -1));
        points.add(new Point(1, 0));
        resource.addShape(points);

        // Adding the upper circle.
        points = new ArrayList<Point>();
        points.add(new Point(0, 5));
        points.add(new Point(1, 4));
        points.add(new Point(0, 3));
        points.add(new Point(-1, 4));
        points.add(new Point(0, 5));
        resource.addShape(points);

        // Adding the right circle.
        points = new ArrayList<Point>();
        points.add(new Point(5, 0));
        points.add(new Point(4, 1));
        points.add(new Point(3, 0));
        points.add(new Point(4, -1));
        points.add(new Point(5, 0));
        resource.addShape(points);
    }
}
