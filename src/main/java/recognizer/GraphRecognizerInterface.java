package recognizer;

import graph.Graph;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Shape;
import shapesAndRecognizers.Stroke;

import java.util.Collection;

/**
 * Created by ankurgupta on 11/24/14.
 */
public interface GraphRecognizerInterface {

    public void classifyStroke(Stroke stroke) throws RecognitionException;

    public Collection<Shape> getAllShapes();
    
    public Collection<Stroke> getAllStrokes();
    
    public void finish() throws RecognitionException;
    
    public Graph getGraph();
    
}
