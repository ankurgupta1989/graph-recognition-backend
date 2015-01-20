package gesture;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ankurgupta on 12/12/14.
 */
public class GesturePoint {

    @JsonProperty
    private int X;

    @JsonProperty
    private int Y;

    @JsonProperty
    private int time;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(X)
               .append(",")
               .append(Y)
               .append(",")
               .append(time);

        return builder.toString();
    }


    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
