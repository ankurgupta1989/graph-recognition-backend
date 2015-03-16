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
