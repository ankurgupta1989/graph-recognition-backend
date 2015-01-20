package recognizer;

import graph.Graph;
import shape.BeautifiedShape;
import shape.Point;

import java.util.List;

/**
 * Created by ankurgupta on 11/24/14.
 */
public interface GraphRecognizerInterface {

    public List<BeautifiedShape> getAllShapes();

    public BeautifiedShape addShape(List<Point> points);

    public void addValue(Point point, Integer value);

    public void addExternalLabel(Point point, String label);

    public void finishDrawing();

    public Graph getGraph();
}
