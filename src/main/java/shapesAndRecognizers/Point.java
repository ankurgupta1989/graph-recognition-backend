package shapesAndRecognizers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Point {
    @JsonProperty
    private int X;

    @JsonProperty
    private int Y;
    
    @JsonProperty
    private int time;

    public Point() {

    }

    public Point(Point p) {
        this.X = p.getX();
        this.Y = p.getY();
        this.time = p.getTime();
    }

    public Point(int X, int Y, int time) {
        this.X = X;
        this.Y = Y;
        this.time = time;
    }

    public double getDistance(Point p) {
        return Math.sqrt((X - p.X)*(X - p.X) + (Y - p.Y)*(Y - p.Y));
    }

    //************************* Getters and Setters ***********************************

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
