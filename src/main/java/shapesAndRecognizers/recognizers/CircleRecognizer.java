package shapesAndRecognizers.recognizers;

import shapesAndRecognizers.*;
import shapesAndRecognizers.shapes.Circle;

import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class CircleRecognizer implements Recognizer {
    
    private static double CIRCLE_THRESHOLD_FOR_RADIUS = 0.3;
    
    private static double CIRCLE_THRESHOLD_FOR_DISTANCE_BETWEEN_FIRST_AND_LAST = 0.1;
    
    private void radiusDistanceCheck(Stroke stroke) throws RecognitionException{
        Point center = stroke.getMean();
        List<Point> points = stroke.getPoints();
        double totalDistanceFromCenter = stroke.distanceFromPoint(center);
        double radius = totalDistanceFromCenter / points.size();
        double totalDistanceFromCenterDiff = 0;
        for (Point point : points) {
            totalDistanceFromCenterDiff += Math.abs(center.getDistance(point) - radius);
        }
        double ratio = totalDistanceFromCenterDiff / totalDistanceFromCenter;
        if (ratio > CIRCLE_THRESHOLD_FOR_RADIUS) {
            throw new RecognitionException();
        }
    }
    
    private void distanceBetweenFirstAndLast(Stroke stroke) throws RecognitionException {
        double distanceBetweenFirstAndLast = stroke.distanceBetweenFirstAndLast();
        double totalDistance = stroke.totalDistance();
        double ratio = distanceBetweenFirstAndLast/totalDistance;
        if (ratio > CIRCLE_THRESHOLD_FOR_DISTANCE_BETWEEN_FIRST_AND_LAST) {
            throw new RecognitionException();
        }
    }
    
    @Override
    public Circle recognize(Stroke stroke) throws RecognitionException {
        radiusDistanceCheck(stroke);
        distanceBetweenFirstAndLast(stroke);
        Point center = stroke.getMean();
        double radius = stroke.distanceFromPoint(center) / stroke.getPoints().size();
        Circle circle = new Circle();
        circle.setCenter(center);
        circle.setRadius(radius);
        return circle;
    }

    @Override
    public Shape recognizeList(List<Stroke> stroke) throws RecognitionException {
        throw new RecognitionException();
    }

}
