package shapesAndRecognizers.shapes;

import shapesAndRecognizers.Shape;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class NodeLabel extends Shape {
    private String label;

    private Rectangle boundingBox;
    
    public NodeLabel() {
        this.identifier = "label";
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
