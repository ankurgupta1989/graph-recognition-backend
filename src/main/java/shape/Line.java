package shape;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ankurgupta on 12/8/14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Line {
    private Point start;
    private Point end;


    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

}
