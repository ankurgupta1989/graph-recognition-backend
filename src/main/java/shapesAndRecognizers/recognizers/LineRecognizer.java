package shapesAndRecognizers.recognizers;

import shapesAndRecognizers.Point;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Recognizer;
import shapesAndRecognizers.Stroke;
import shapesAndRecognizers.shapes.Line;

import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class LineRecognizer implements Recognizer {
    
    private static double LINE_THRESHOLD = 0.8;
    
    @Override
    public Line recognize(Stroke stroke) throws RecognitionException {
        double distanceBetweenFirstAndLast = stroke.distanceBetweenFirstAndLast();
        double totalDistance = stroke.totalDistance();
        double ratio = distanceBetweenFirstAndLast/totalDistance;
        if (ratio < LINE_THRESHOLD) {
            throw new RecognitionException();
        }
        else {
            Line line = new Line();
            List<Point> points = stroke.getPoints();
            Point lastPoint = points.get(points.size() - 1);
            line.setEndPoint(lastPoint);
            Point firstPoint = points.get(0);
            line.setStartPoint(firstPoint);
            return line;
        }
    }
    
}
