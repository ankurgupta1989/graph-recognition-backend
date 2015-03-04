package shapesAndRecognizers;

/**
 * Created by ankurgupta on 3/4/15.
 */
public interface Recognizer {
    
    public Shape recognize(Stroke stroke) throws RecognitionException;
    
}
