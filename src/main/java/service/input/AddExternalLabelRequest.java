package service.input;

import shape.Point;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class AddExternalLabelRequest {
    private Point point;

    private String externalLabel;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getExternalLabel() {
        return externalLabel;
    }

    public void setExternalLabel(String externalLabel) {
        this.externalLabel = externalLabel;
    }
}
