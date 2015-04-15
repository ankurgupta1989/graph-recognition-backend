package shapesAndRecognizers.shapes;

import shapesAndRecognizers.Point;
import shapesAndRecognizers.Shape;

/**
 * Created by ankurgupta on 3/15/15.
 */
public class Rectangle extends Shape{
    private Point bottomLeft;
    private Point topRight;

    public Rectangle() {
        this.identifier = "rectangle";
    }
    
    public double size() {
        double width = topRight.getX() - bottomLeft.getX();
        double height = topRight.getY() - bottomLeft.getY();
        return width*height;
    }
    
    public double biggerDimension() {
        double width = topRight.getX() - bottomLeft.getX();
        double height = topRight.getY() - bottomLeft.getY();
        return Math.max(width, height);
    }
    
    public Point getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    public void setTopRight(Point topRight) {
        this.topRight = topRight;
    }
}
