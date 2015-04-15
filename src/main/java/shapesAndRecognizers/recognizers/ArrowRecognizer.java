package shapesAndRecognizers.recognizers;

import shapesAndRecognizers.*;
import shapesAndRecognizers.shapes.Arrow;
import shapesAndRecognizers.shapes.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class ArrowRecognizer implements Recognizer {
    
    private static double ARROW_THRESHOLD = 0.5;
    
    @Override
    public Arrow recognize(Stroke stroke) throws RecognitionException {
        List<Point> points = stroke.getPoints();
        int maxDistancePointIndex = -1;
        Point firstPoint = points.get(0);
        double maxDistance = 0;
        
        for (int i = 1; i < points.size(); i++) {
            double dist = firstPoint.getDistance(points.get(i));
            if (maxDistancePointIndex == -1 || dist > maxDistance) {
                maxDistancePointIndex = i;
                maxDistance = dist;
            }
        }
        
        List<Point> newPoints = new ArrayList<Point>();
        for (int i = 0; i < maxDistancePointIndex; i++) {
            newPoints.add(new Point(points.get(i)));
        }
        
        Stroke newStroke = new Stroke(newPoints);
        LineRecognizer lineRecognizer = new LineRecognizer();
        Line recognizedLine = lineRecognizer.recognize(newStroke);

        List<Point> remainingPoints = new ArrayList<Point>();
        for (int i = maxDistancePointIndex; i < points.size(); i++) {
            remainingPoints.add(new Point(points.get(i)));
        }
        double dist1 = getMaximumDistance(remainingPoints, recognizedLine, true);
        double dist2 = getMaximumDistance(remainingPoints, recognizedLine, true);
        
        double lineDistance = recognizedLine.getStartPoint().getDistance(recognizedLine.getEndPoint());
        if (dist1/lineDistance > ARROW_THRESHOLD || dist1 == 0) {
            throw new RecognitionException();
        }
        if (dist2/lineDistance > ARROW_THRESHOLD || dist2 == 0) {
            throw new RecognitionException();
        }
        
        Arrow arrow =  new Arrow();
        arrow.setStartPoint(recognizedLine.getStartPoint());
        arrow.setEndPoint(recognizedLine.getEndPoint());
        return arrow;
    }

    @Override
    public Shape recognizeList(List<Stroke> stroke) throws RecognitionException {
        throw new RecognitionException();
    }

    private double getMaximumDistance(List<Point> remainingPoints, Line line, boolean side) {
        double maxDistance = 0;
        for (Point point : remainingPoints) {
            if (side == RecognizerUtility.getSide(line.getStartPoint(), line.getEndPoint(), point)) {
                maxDistance = Math.max(maxDistance, RecognizerUtility.getDistance(line.getStartPoint(), line.getEndPoint(), point));
            }
        }
        return maxDistance;
    }

}
