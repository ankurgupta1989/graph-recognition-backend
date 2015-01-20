package recognizer;

import graph.Graph;
import shape.BeautifiedShape;
import shape.Circle;
import shape.Point;
import shape.Shape;

import java.util.*;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class GraphRecognizerImplementation implements GraphRecognizerInterface {

    private Map<Integer, Shape> identifier;
    private List<Integer> circles;
    private List<Integer> lines;
    private Integer count;

    private Graph graph;

    public GraphRecognizerImplementation() {
        this.count = 0;
        this.identifier = new HashMap<Integer, Shape>();
        this.graph = new Graph();
        this.circles = new ArrayList<Integer>();
        this.lines = new ArrayList<Integer>();
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public List<BeautifiedShape> getAllShapes() {
        List<BeautifiedShape> list = new ArrayList<BeautifiedShape>();
        for(Shape shape : identifier.values()) {
            list.add(shape.getShape());
        }
        return list;
    }

    public BeautifiedShape addShape(List<Point> points) {
        Shape shape = new Shape(points);
        this.count++;
        this.identifier.put(count, shape);
        shape.setIdentifier(count);

        Shape.Label label= shape.getLabel();

        if (Shape.Label.CIRCLE.equals(label)) {
            circles.add(shape.getIdentifier());
        }

        else if (Shape.Label.LINE.equals(label)) {
            lines.add(shape.getIdentifier());
        }

        return shape.getShape();
    }

    public void addValue(Point point, Integer value) {
        Integer closestShapeIdentifier = getClosestShape(point);
        if (closestShapeIdentifier != null) {
            Shape shape = identifier.get(closestShapeIdentifier);
            shape.setValue(value);
            shape.getShape().setValue(value);
        }
    }

    public void addExternalLabel(Point point, String label) {
        Integer closestShapeIdentifier = getClosestShape(point);
        if (closestShapeIdentifier != null) {
            Shape shape = identifier.get(closestShapeIdentifier);
            shape.setExternalLabel(label);
            shape.getShape().setLabel(label);
        }
    }

    public void finishDrawing() {
        for (Integer lineId : lines) {
            Integer circleId1 = getClosestCircle(lineId, new ArrayList<Integer>());
            Shape circle1 = identifier.get(circleId1);
            Integer circleId2 = getClosestCircle(lineId, Collections.singletonList(circleId1));
            Shape circle2 = identifier.get(circleId2);
            Shape line = identifier.get(lineId);
            BeautifiedShape bShape = line.getShape();
            Circle c1 = circle1.getShape().getCircle();
            Circle c2 = circle2.getShape().getCircle();
            bShape.getLine().setStart(new Point(c1.getCenter()));
            bShape.getLine().setEnd(new Point(c2.getCenter()));
            // Add the edge in the graph.
            graph.addEdge(circleId1, circle1.getValue(), circle1.getExternalLabel(),
                    circleId2, circle2.getValue(), circle2.getExternalLabel(),
                    line.getValue(), line.getExternalLabel());
        }
    }

    //************************************ Private functions *************************************************

    private Integer getClosestShape(Point point) {
        double minDistance = Double.MAX_VALUE;
        Shape minShape = null;
        for (Shape shape : identifier.values()) {
            double dist = shape.getMinimumDistance(point);
            if (dist < minDistance) {
                minDistance = dist;
                minShape = shape;
            }
        }
        if (minShape!= null) {
            return minShape.getIdentifier();
        }
        return null;
    }

    private Integer getClosestCircle(Integer lineId, List<Integer> alreadyLookedCircleIds) {
        Shape line = identifier.get(lineId);
        double minDistance = Double.MAX_VALUE;
        Integer minCircleId = null;
        for (Integer circleId : circles) {
            if (alreadyLookedCircleIds.contains(circleId)) {
                continue;
            }
            Shape circle = identifier.get(circleId);
            double dist = line.getMinimumDistance(circle);
            if (dist < minDistance) {
                minDistance = dist;
                minCircleId = circleId;
            }
        }
        return minCircleId;
    }
}
