package service;

import graph.Edge;
import graph.Graph;
import graphAlgorithms.Kruskal;
import recognizer.GraphRecognizerImplementation;
import recognizer.GraphRecognizerInterface;
import recognizer.RecognitionUtility;
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
import java.util.ArrayList;
import java.util.Collection;
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
    @Path("verify_MST")
    public synchronized String runAlgorithm(RunAlgorithmRequest request) {
        Graph graph = recognizer.getGraph();
        StringBuilder output = new StringBuilder();
        Collection<Shape> shapeList = recognizer.getAllShapes();
        Kruskal kruskal = new Kruskal();
        List<Edge> edgeList = new ArrayList<Edge>();
        kruskal.run(graph, null, output, edgeList);
        boolean isLabellingCorrect = true;
        for (Edge edge : edgeList) {
            if (! RecognitionUtility.isLabellingCorrect(edge.getLabel(), shapeList)) {
                isLabellingCorrect = false;
                break;
            }
        }
        if (isLabellingCorrect) {
            RecognitionUtility.markBoldEdges("green", shapeList);
        }
        else {
            RecognitionUtility.markBoldEdges("red", shapeList);
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
    public synchronized Collection<Shape> getAllShapes() {
        return recognizer.getAllShapes();
    }
    
    @POST
    @Path("get_all_strokes")
    public synchronized Collection<Stroke> getAllStrokes() {
        return recognizer.getAllStrokes();
    }
    
    @POST
    @Path("mark_edge")
    public synchronized void markEdge(Point point) {
        Collection<Shape> shapeList = recognizer.getAllShapes();
        RecognitionUtility.boldClosestEdge(shapeList, point);
    }
    
    @POST
    @Path("unmark_edges")
    public synchronized void unmarkEdges() {
        Collection<Shape> shapeList = recognizer.getAllShapes();
        RecognitionUtility.unmarkEdges(shapeList);
    }
}

