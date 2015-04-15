package shapesAndRecognizers.shapes;

import shapesAndRecognizers.Point;
import shapesAndRecognizers.Shape;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class Circle extends Shape {
    private double radius;
    private Point center;
    private String label;
    
    public Circle() {
        this.identifier = "circle";
    }
    
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }  
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Circle)) {
            return false;
        }
        Circle other = (Circle) obj;
        return this.radius == other.radius && this.center.equals(other.center);
    }
}
