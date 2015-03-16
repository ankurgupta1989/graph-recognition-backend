package shapesAndRecognizers.shapes;

import shapesAndRecognizers.Shape;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class NodeValue extends Shape {
    private int number;

    private Rectangle boundingBox;
    
    public NodeValue() {
        this.identifier = "number";
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
}
