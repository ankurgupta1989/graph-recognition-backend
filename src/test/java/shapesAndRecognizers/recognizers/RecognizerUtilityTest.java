package shapesAndRecognizers.recognizers;

import org.junit.Assert;
import org.junit.Test;
import shapesAndRecognizers.Point;

/**
 * Created by ankurgupta on 3/15/15.
 */
public class RecognizerUtilityTest {
    
    @Test
    public void testGetSide1() {
        
        Point first = new Point(1, 1, 0);
        Point second = new Point(3, 3, 0);
        
        Point newPoint = new Point(2, 0 , 0);
        boolean res1 = RecognizerUtility.getSide(first, second, newPoint);
        
        newPoint = new Point(0, 2, 0);
        boolean res2 = RecognizerUtility.getSide(first, second, newPoint);
        
        if (res1 == res2) {
            Assert.fail();
        }
    }

    @Test
    public void testGetSide2() {

        Point first = new Point(1, 1, 0);
        Point second = new Point(1, 3, 0);

        Point newPoint = new Point(2, 0 , 0);
        boolean res1 = RecognizerUtility.getSide(first, second, newPoint);

        newPoint = new Point(0, 2, 0);
        boolean res2 = RecognizerUtility.getSide(first, second, newPoint);

        if (res1 == res2) {
            Assert.fail();
        }
    }

    @Test
    public void testGetDistance1() {

        Point first = new Point(1, 1, 0);
        Point second = new Point(3, 3, 0);

        Point newPoint = new Point(2, 0 , 0);
        double res1 = RecognizerUtility.getDistance(first, second, newPoint);

        newPoint = new Point(0, 2, 0);
        double res2 = RecognizerUtility.getDistance(first, second, newPoint);

        if (res1 != res2) {
            Assert.fail();
        }
    }
}
