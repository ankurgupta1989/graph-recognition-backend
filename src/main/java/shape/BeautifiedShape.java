package shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankurgupta on 12/8/14.
 */
public class BeautifiedShape {

    @JsonProperty
    private boolean circleOrLine;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Line line;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Circle circle;

    private Integer value;

    private String label;

    public BeautifiedShape(boolean circleOrLine, Line line, Circle circle) {
        this.circleOrLine = circleOrLine;
        this.line = line;
        this.circle = circle;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public boolean isCircleOrLine() {
        return circleOrLine;
    }

    public void setCircleOrLine(boolean circleOrLine) {
        this.circleOrLine = circleOrLine;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
