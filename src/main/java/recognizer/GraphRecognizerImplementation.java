package recognizer;

import graph.Graph;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Recognizer;
import shapesAndRecognizers.Shape;
import shapesAndRecognizers.Stroke;
import shapesAndRecognizers.recognizers.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class GraphRecognizerImplementation implements GraphRecognizerInterface {

    private static List<Recognizer> recognizerList;
    
    static {
        recognizerList = new ArrayList<Recognizer>();
        recognizerList.add(new LineRecognizer());
        recognizerList.add(new CircleRecognizer());
        recognizerList.add(new ArrowRecognizer());
        recognizerList.add(new NodeLabelAndValueRecognizer());
    }
    
    private List<Shape> shapeList;
    
    private Graph graph;
    
    public GraphRecognizerImplementation() {
        this.shapeList = new ArrayList<Shape>();
        this.graph = null;
    }
    
    @Override
    public void classifyStroke(Stroke stroke) throws RecognitionException {
        Shape shape = null;
        for (Recognizer recognizer : recognizerList) {
            try {
                shape = recognizer.recognize(stroke);
                break;
            }
            catch(RecognitionException e) {
                // ok try the next one;
            }
        }
        if (shape == null) {
            throw new RecognitionException();
        }
        this.shapeList.add(shape);
    }

    @Override
    public List<Shape> getAllShapes() {
        return shapeList;
    }

    @Override
    public void finish() throws RecognitionException {
        this.graph = RecognitionUtility.recognizeGraph(shapeList);
    }


    @Override
    public Graph getGraph() {
        return this.graph;
    }
}
