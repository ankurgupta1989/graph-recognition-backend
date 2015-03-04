package shapesAndRecognizers;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class Shape {
    
    private String identifier;
    
    private String type;
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Shape)) {
            return false;
        }
        Shape shape = (Shape) obj;
        return shape.identifier.equals(this.identifier);
    }
}
