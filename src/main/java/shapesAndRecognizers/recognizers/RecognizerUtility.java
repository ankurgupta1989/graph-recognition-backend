package shapesAndRecognizers.recognizers;

import shapesAndRecognizers.Point;

/**
 * Created by ankurgupta on 3/15/15.
 */
public class RecognizerUtility {
    
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
