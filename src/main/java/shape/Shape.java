package shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Shape {

    public static enum Label {
        CIRCLE,
        LINE,
        NO_LABEL
    }

    private BeautifiedShape shape;

    private List<Point> points;

    private Label label;

    private Integer value;

    private String externalLabel;

    private Integer identifier;

    public Shape() {
        points = new ArrayList<Point>();
    }

    public Shape(List<Point> pointsList) {
        this.points = pointsList;
        putLabel();
    }

    public double getMinimumDistance(Point point) {
        double minDistance = Double.MAX_VALUE;
        for (Point p : points) {
            minDistance = Math.min(minDistance, p.getDistance(point));
        }
        return minDistance;
    }

    public double getMinimumDistance(Shape shape) {
        double minDistance = Double.MAX_VALUE;
        for (Point thisPoint : this.points) {
            for (Point otherPoint : shape.points) {
                minDistance = Math.min(minDistance, thisPoint.getDistance(otherPoint));
            }
        }
        return minDistance;
    }

    //************************************** Private functions **********************************


    /**
     * Identifies the type of the shape.
     * @return
     */
    private Label putLabel() {

        if (this.points.isEmpty()) {
            return Label.NO_LABEL;
        }
        Integer pointsSize = points.size();
        Integer mid = pointsSize/2;
        Point midPoint = points.get(mid);
        Point endPoint = points.get(pointsSize - 1);
        Point startPoint = points.get(0);
        double distStartToEnd = startPoint.getDistance(endPoint);
        double distStartToMid = startPoint.getDistance(midPoint);
        if (distStartToEnd > distStartToMid) {
            this.label = Label.LINE;
            populateLine();
        } else {
            this.label = Label.CIRCLE;
            populateCircle();
        }
        return this.label;
    }

    private void populateCircle() {
        long xMean = 0;
        long yMean = 0;
        for (Point p : points) {
            xMean += p.getX();
            yMean += p.getY();
        }
        xMean /= points.size();
        yMean /= points.size();

        Point center = new Point((int)xMean, (int)yMean);
        long dist=0;
        for (Point p: points) {
            dist += center.getDistance(p);
        }
        dist /= points.size();

        Circle circle = new Circle((int)dist, center);

        this.shape = new BeautifiedShape(true, null, circle);
    }

    private void populateLine() {
        Line line = new Line(points.get(0), points.get(points.size() - 1));
        this.shape = new BeautifiedShape(false, line, null);
    }

    //*************************************** Getters and Setters *********************************

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;

    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }


    public String getExternalLabel() {
        return externalLabel;
    }

    public void setExternalLabel(String externalLabel) {
        this.externalLabel = externalLabel;
    }


    public BeautifiedShape getShape() {
        return shape;
    }

    public void setShape(BeautifiedShape shape) {
        this.shape = shape;
    }
}
