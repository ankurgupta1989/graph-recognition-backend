package shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankurgupta on 12/8/14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Circle {
    @JsonProperty
    private int radius;
    private Point center;


    public Circle(int radius, Point center) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
}
