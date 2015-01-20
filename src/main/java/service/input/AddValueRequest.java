package service.input;

import shape.Point;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class AddValueRequest {
    private Point point;
    private Integer value;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
