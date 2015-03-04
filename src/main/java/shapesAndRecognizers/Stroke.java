package shapesAndRecognizers;

import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class Stroke {

    private List<Point> points;
    
    public Stroke(List<Point> points) {
        this.points = points;
    }


    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
