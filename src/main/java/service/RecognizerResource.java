package service;

import graph.Graph;
import graphAlgorithms.BFS;
import recognizer.GraphRecognizerImplementation;
import recognizer.GraphRecognizerInterface;
import service.input.RunAlgorithmRequest;
import shapesAndRecognizers.Point;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Shape;
import shapesAndRecognizers.Stroke;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public synchronized void addShape(List<Point> pointList) throws RecognitionException {
        Stroke stroke = new Stroke(pointList);
        recognizer.classifyStroke(stroke);
    }

    @POST
    @Path("finish")
    public synchronized void finish() throws RecognitionException {
        recognizer.finish();
    }

    @POST
    @Path("run_algorithm")
    public synchronized String runAlgorithm(RunAlgorithmRequest request) {
        Graph graph = recognizer.getGraph();
        String algorithmName = request.getAlgorithmName();
        StringBuilder output = new StringBuilder();
        if ("bfs".equals(algorithmName)) {
            BFS bfs = new BFS();
            bfs.run(graph, request.getArguments(), output);
        }
        return output.toString();
    }

    @POST
    @Path("start")
    public synchronized void start() {
        recognizer = new GraphRecognizerImplementation();
    }

    @POST
    @Path("get_all_shapes")
    public synchronized List<Shape> getAllShapes() {
        return recognizer.getAllShapes();
    }
}

