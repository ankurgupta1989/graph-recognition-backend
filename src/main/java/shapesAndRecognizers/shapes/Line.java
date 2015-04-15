package shapesAndRecognizers.shapes;

import shapesAndRecognizers.Point;
import shapesAndRecognizers.Shape;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class Line extends Shape {
    private Point startPoint;
    private Point endPoint;
    
    public Line() {
        this.identifier = "line";
    }

    //Compute the dot product AB ⋅ BC
    private double dot(Point A, Point B, Point C){
        Point AB = new Point();
        Point BC = new Point();
        AB.setX(B.getX() - A.getX());
        AB.setY(B.getY() - A.getY());
        BC.setX(C.getX() - B.getX());
        BC.setY(C.getY() - B.getY());
        double dot = AB.getX() * BC.getX() + AB.getY() * BC.getY();
        return dot;
    }

    // Compute the cross product AB x AC
    private double cross(Point A, Point B, Point C){
        Point AB = new Point();
        Point AC = new Point();
        AB.setX(B.getX() - A.getX());
        AB.setY(B.getY() - A.getY());
        AC.setX(C.getX() - A.getX());
        AC.setY(C.getY() - A.getY());
        double cross = AB.getX() * AC.getY() - AB.getY() * AC.getX();
        return cross;
    }

    // Compute the distance from A to B
    private double distance(Point A, Point B){
        double d1 = A.getX() - B.getX();
        double d2 = A.getY() - B.getY();
        return Math.sqrt(d1*d1+d2*d2);
    }

    //Compute the distance from AB to C
    //if isSegment is true, AB is a segment, not a line.
    public double linePointDist(Point A, Point B, Point C, boolean isSegment){
        double dist = cross(A,B,C) / distance(A,B);
        if(isSegment){
            double dot1 = dot(A,B,C);
            if(dot1 > 0)return distance(B,C);
            double dot2 = dot(B,A,C);
            if(dot2 > 0)return distance(A,C);
        }
        return Math.abs(dist);
    }
    
    public double getDistance(Point point) {
        return  linePointDist(this.getStartPoint(), this.endPoint, point, true);
    }
    
    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
