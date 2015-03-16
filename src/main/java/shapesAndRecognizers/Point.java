package shapesAndRecognizers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Point {
    @JsonProperty
    private double X;

    @JsonProperty
    private double Y;
    
    @JsonProperty
    private int time;

    public Point() {

    }

    public Point(Point p) {
        this.X = p.getX();
        this.Y = p.getY();
        this.time = p.getTime();
    }

    public Point(double X, double Y, int time) {
        this.X = X;
        this.Y = Y;
        this.time = time;
    }

    public double getDistance(Point p) {
        return Math.sqrt((X - p.X)*(X - p.X) + (Y - p.Y)*(Y - p.Y));
    }
    
    public Point getMedian(Point p) {
        double medianX = (X + p.X)/2;
        double medianY = (Y + p.Y)/2;
        return new Point(medianX, medianY, 0);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return other.X == this.X && other.Y == this.Y;
    }

    //************************* Getters and Setters ***********************************

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
