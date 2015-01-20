package shape;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Point {

    @JsonProperty
    private int X;

    @JsonProperty
    private int Y;

    public Point() {

    }

    public Point(Point p) {
        this.X = p.getX();
        this.Y = p.getY();
    }

    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
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

}
