package shapesAndRecognizers.recognizers;

import shapesAndRecognizers.Point;
import shapesAndRecognizers.Stroke;
import shapesAndRecognizers.shapes.Rectangle;

import java.util.List;

/**
 * Created by ankurgupta on 3/15/15.
 */
public class RecognizerUtility {
    
    public static Rectangle getBoundingBox(List<Stroke> strokeList) {
        double xMin = Integer.MAX_VALUE;
        double xMax = Integer.MIN_VALUE;
        double yMin = Integer.MAX_VALUE;
        double yMax = Integer.MIN_VALUE;
        for (Stroke stroke : strokeList) {
            Rectangle box = stroke.getBoundingBox();
            Point bottomLeft = box.getBottomLeft();
            Point topRight = box.getTopRight();
            xMin = Math.min(xMin, bottomLeft.getX());
            yMin = Math.min(yMin, bottomLeft.getY());
            xMax = Math.max(xMax, topRight.getX());
            yMax = Math.max(yMax, topRight.getY());
        }
        Point bottomLeft = new Point(xMin, yMin, 0);
        Point topRight = new Point(xMax, yMax, 0);
        Rectangle rect = new Rectangle();
        rect.setBottomLeft(bottomLeft);
        rect.setTopRight(topRight);
        return rect;
    }
    
    public static boolean getSide(Point first, Point second, Point newPoint) {
        double X1 = first.getX();
        double Y1 = first.getY();
        
        double X2 = second.getX();
        double Y2 = second.getY();
        
        double a = 0;
        double b = 0;
        double c = 0;
        if (X1 == X2) {
            b = 0;
            a = 1;
            c = -X1;
        }
        else {
            b = 1;
            double m = (Y1 - Y2)/(X1 - X2);
            a = -m;
            double c1 = (Y2*X1 - Y1*X2)/(X1 - X2);
            c = -c1;
        }
        double temp = a*newPoint.getX() + b*newPoint.getY() + c;
        return temp > 0;
    }
    
    public static double getDistance(Point first, Point second, Point newPoint) {
        double X1 = first.getX();
        double Y1 = first.getY();

        double X2 = second.getX();
        double Y2 = second.getY();

        double X0 = newPoint.getX();
        double Y0 = newPoint.getY();
        
        double temp = (Y2 - Y1)*X0 - (X2 - X1)*Y0 + X2*Y1 - Y2*X1;
        temp = Math.abs(temp);
        return temp/second.getDistance(first);
    }
}
