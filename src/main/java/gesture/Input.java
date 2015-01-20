package gesture;

import java.util.List;

/**
 * Created by ankurgupta on 12/12/14.
 */
public class Input {

    private List<GesturePoint> pointList;
    private String outputClass;
    private String image;

    public List<GesturePoint> getPointList() {
        return pointList;
    }

    public void setPointList(List<GesturePoint> pointList) {
        this.pointList = pointList;
    }

    public String getOutputClass() {
        return outputClass;
    }

    public void setOutputClass(String outputClass) {
        this.outputClass = outputClass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
