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
