package recognizer;

import graph.Graph;
import shapesAndRecognizers.Point;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Shape;
import shapesAndRecognizers.shapes.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class RecognitionUtility {

    public static void unmarkEdges(Collection<Shape> shapeList) {
        for (Shape shape : shapeList) {
            if (shape instanceof Line || shape instanceof Arrow) {
                shape.setEffect("");
            }
        }
    }
    
    public static boolean isLabellingCorrect(String label, Collection<Shape> shapeList) {
        
        for (Shape shape : shapeList) {
            if (shape instanceof Line || shape instanceof Arrow) {
                if (shape.getLabel().equals(label)) {
                    if (! "bold".equals(shape.getEffect())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void markBoldEdges(String color, Collection<Shape> shapeList) {
        for (Shape shape : shapeList) {
            if (shape instanceof Line || shape instanceof Arrow) {
                if ("bold".equals(shape.getEffect())) {
                    shape.setEffect(color);
                }
            }
        }
    }
    
    public static void boldClosestEdge(Collection<Shape> shapeList, Point point) {
        Shape closestShape = null;
        double minDistance = Double.MAX_VALUE;
        for (Shape shape : shapeList) {
            if (shape instanceof Line) {
                Line line = (Line) shape;
                double thisMin = line.getDistance(point);
                if (thisMin < minDistance) {
                    closestShape = shape;
                    minDistance = thisMin;
                }
            }
        }
        if (closestShape != null) {
            closestShape.setEffect("bold");
        }
        
    }
    
    private static Point getPointToCompare(Shape shape) throws RecognitionException {
        Point pointToCompare = null;
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            pointToCompare = circle.getCenter();
        }
        else if (shape instanceof Line) {
            Line line = (Line) shape;
            pointToCompare = line.getStartPoint().getMedian(line.getEndPoint());
        }
        else if (shape instanceof Arrow) {
            Arrow arrow = (Arrow) shape;
            pointToCompare = arrow.getStartPoint().getMedian(arrow.getEndPoint());
        }

        if (pointToCompare == null) {
            throw new RecognitionException();
        }
        return pointToCompare;
    }
    
    private static String findShapeLabel(Shape shape, List<Shape> shapeList) throws RecognitionException {
        Shape labelShape = null;
        double distance = Double.MAX_VALUE;

        Point pointToCompare = getPointToCompare(shape);
        
        for (Shape candidateLabelShape : shapeList) {
            if (candidateLabelShape instanceof NodeLabel) {
                NodeLabel candidateLabel = (NodeLabel) candidateLabelShape;
                Point candidateLabelLocation = candidateLabel.getBoundingBox().getBottomLeft()
                        .getMedian(candidateLabel.getBoundingBox().getTopRight());
                double thisDistance = candidateLabelLocation.getDistance(pointToCompare);
                if (thisDistance < distance) {
                    distance = thisDistance;
                    labelShape = candidateLabelShape;
                }
            }
        }
        if (labelShape == null) {
            throw new RecognitionException();
        }
        NodeLabel label = (NodeLabel) labelShape;
        return label.getLabel();
    }

    private static Integer findShapeValue(Shape shape, List<Shape> shapeList) throws RecognitionException {
        Shape valueShape = null;
        double distance = Double.MAX_VALUE;

        Point pointToCompare = getPointToCompare(shape);

        for (Shape candidateValueShape : shapeList) {
            if (candidateValueShape instanceof NodeValue) {
                NodeValue candidateValue = (NodeValue) candidateValueShape;
                Point candidateValueLocation = candidateValue.getBoundingBox().getBottomLeft()
                        .getMedian(candidateValue.getBoundingBox().getTopRight());
                double thisDistance = candidateValueLocation.getDistance(pointToCompare);
                if (thisDistance < distance) {
                    distance = thisDistance;
                    valueShape = candidateValueShape;
                }
            }
        }
        if (valueShape == null) {
            return null;
        }
        NodeValue value = (NodeValue) valueShape;
        return value.getNumber();
    }
    
    private static Circle findNearestCircle(Shape shape, List<Shape> shapeList, Circle excludeCircle) throws RecognitionException {
        
        if (! (shape instanceof Line || shape instanceof Arrow)) {
            throw new RecognitionException();
        }
        Point startPoint = null;
        Point endPoint = null;
        if (shape instanceof Line) {
            Line line = (Line) shape;
            startPoint = line.getStartPoint();
            endPoint = line.getEndPoint();
        }
        if (shape instanceof Arrow) {
            Arrow arrow = (Arrow) shape;
            startPoint = arrow.getStartPoint();
            endPoint = arrow.getEndPoint();
        }
        
        
        double distance = Double.MAX_VALUE;
        Circle nearestCircle = null;
        for (Shape candidateNode : shapeList) {
            if (candidateNode instanceof Circle) {
                Circle candidateCircle = (Circle) candidateNode;
                if (excludeCircle != null && candidateCircle.equals(excludeCircle)) {
                    continue;
                }
                double candidateDist = 0;
                if (excludeCircle == null) {
                    candidateDist = startPoint.getDistance(candidateCircle.getCenter());
                }
                else {
                    candidateDist = endPoint.getDistance(candidateCircle.getCenter());
                }
                if (candidateDist < distance) {
                    distance = candidateDist;
                    nearestCircle = candidateCircle;
                }
            }
        }
        return nearestCircle;
    }

    /**
     * The function mutates shapeList.
     * @param shapeList list of shapes
     * @return Graph recognized graph.
     */
    public static Graph recognizeGraph(List<Shape> shapeList) throws RecognitionException {
        
        Graph graph = new Graph();
        
        // find all nodes and initialize it.
        for(Shape shape : shapeList) {
            if (shape instanceof Circle) {
                String nodeLabel = findShapeLabel(shape, shapeList);
                Integer nodeValue = findShapeValue(shape, shapeList);
                graph.addNode(nodeLabel, nodeValue);
            }
        }
        
        // find all edges and associate it with corresponding nodes. Change the edge points for beautification.
        for (Shape shape : shapeList) {
            if (shape instanceof Line || shape instanceof Arrow) {
//                String lineLabel = findShapeLabel(shape, shapeList);
                
                // TODO Remove the hack in the following lines.
                String lineLabel = Integer.toString(new Random().nextInt());
                shape.setLabel(lineLabel);
                // Hack over
                
                Integer lineValue = findShapeValue(shape, shapeList);
                Circle firstCircle = findNearestCircle(shape, shapeList, null);
                Circle secondCircle = findNearestCircle(shape, shapeList, firstCircle);
                String firstCircleLabel = findShapeLabel(firstCircle, shapeList);
                String secondCircleLabel = findShapeLabel(secondCircle, shapeList);
                if (shape instanceof Line) {
                    graph.addUndirectedEdge(firstCircleLabel, secondCircleLabel, lineLabel, lineValue);
                    Line line = (Line) shape;
                    line.setStartPoint(firstCircle.getCenter());
                    line.setEndPoint(secondCircle.getCenter());
                }
                else {
                    graph.addDirectedEdge(firstCircleLabel, secondCircleLabel, lineLabel, lineValue);
                    Arrow arrow = (Arrow) shape;
                    arrow.setStartPoint(firstCircle.getCenter());
                    arrow.setEndPoint(secondCircle.getCenter());
                }
            }
        }
        
        return graph;
    }
}
