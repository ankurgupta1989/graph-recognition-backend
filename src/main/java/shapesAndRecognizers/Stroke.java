package shapesAndRecognizers;

import shapesAndRecognizers.shapes.Rectangle;

import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class Stroke {

    private List<Point> points;
    
    public Stroke(List<Point> points) {
        this.points = points;
    }


    public Point getMean() {
        double xCenter = 0;
        double yCenter = 0;
        
        for (Point point : points) {
            xCenter += point.getX();
            yCenter += point.getY();
        }
        return new Point(xCenter/points.size(), yCenter/points.size(), 0);
    }

    public double totalDistance() {
        Point lastPoint = points.get(0);
        double totalDistance = 0;
        for (int index = 1; index < points.size(); index++) {
            totalDistance += points.get(index).getDistance(lastPoint);
            lastPoint = points.get(index);
        }
        return totalDistance;
    }

    public double distanceBetweenFirstAndLast() {
        Point firstPoint = points.get(0);
        Point lastPoint = points.get(points.size() - 1);
        return firstPoint.getDistance(lastPoint);
    }
    
    public double distanceFromPoint(Point otherPoint) {
        double totalDistance = 0;
        for (Point point : points) {
            totalDistance += otherPoint.getDistance(point);
        }
        return totalDistance;
    }
    
    public Rectangle getBoundingBox() {
        
        double leftMost = Double.MAX_VALUE;
        for (Point point : points) {
            leftMost = Math.min(leftMost, point.getX());
        }

        double lowerMost = Double.MAX_VALUE;
        for (Point point : points) {
            lowerMost = Math.min(lowerMost, point.getY());
        }

        double rightMost = Double.MIN_VALUE;
        for (Point point : points) {
            rightMost = Math.max(rightMost, point.getX());
        }

        double upperMost = Double.MIN_VALUE;
        for (Point point : points) {
            upperMost = Math.max(upperMost, point.getY());
        }
        
        Rectangle rectangle = new Rectangle();
        rectangle.setBottomLeft(new Point(leftMost, lowerMost, 0));
        rectangle.setTopRight(new Point(rightMost, upperMost, 0));
        return rectangle;
    }
    
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
