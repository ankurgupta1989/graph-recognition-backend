package shapesAndRecognizers;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class Shape {
    
    protected String identifier;
    
    private String type;
    
    private String effect;

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
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
