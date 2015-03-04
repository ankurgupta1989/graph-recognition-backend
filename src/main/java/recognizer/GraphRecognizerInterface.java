package recognizer;

import graph.Graph;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Shape;
import shapesAndRecognizers.Stroke;

import java.util.List;

/**
 * Created by ankurgupta on 11/24/14.
 */
public interface GraphRecognizerInterface {

    public void classifyStroke(Stroke stroke) throws RecognitionException;

    public List<Shape> getAllShapes();
    
    public void finish();
    
    public Graph getGraph();
    
}
