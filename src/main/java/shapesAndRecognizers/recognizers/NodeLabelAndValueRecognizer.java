package shapesAndRecognizers.recognizers;

import myscript.ws.MyScriptCloud;
import myscript.ws.RecognitionListener;
import org.apache.commons.lang3.math.NumberUtils;
import shapesAndRecognizers.*;
import shapesAndRecognizers.shapes.NodeLabel;
import shapesAndRecognizers.shapes.NodeValue;

import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class NodeLabelAndValueRecognizer implements Recognizer, RecognitionListener {

    static final String apiKey = "807538b7-e805-4258-8c62-0b7b8227af1c";
    static final String recognitionCloudURL = "http://cloud.myscript.com/api/myscript/v2.0/hwr/doSimpleRecognition.json";

    @Override
    public Shape recognize(Stroke stroke) throws RecognitionException {
        
        MyScriptCloud hwr = new MyScriptCloud(recognitionCloudURL, apiKey);
        hwr.setListener(this);

        myscript.ws.api.Stroke apiStroke = convertStroke(stroke);
        
        String recognized = hwr.addStroke(apiStroke);
        if (! NumberUtils.isNumber(recognized)) {
            NodeLabel nodeLabel = new NodeLabel();
            nodeLabel.setLabel(recognized);
            nodeLabel.setBoundingBox(stroke.getBoundingBox());
            return nodeLabel;
        }
        else {
            NodeValue nodeValue = new NodeValue();
            nodeValue.setNumber(Integer.valueOf(recognized));
            nodeValue.setBoundingBox(stroke.getBoundingBox());
            return nodeValue;
        }
    }

    public static myscript.ws.api.Stroke convertStroke(Stroke stroke) {
        List<Point> points = stroke.getPoints();
        myscript.ws.api.Point[] apiPoints = new myscript.ws.api.Point[points.size()];
        int index = 0;
        for (Point point : points) {
            apiPoints[index++] = new myscript.ws.api.Point((float)point.getX(), (float)point.getY());
        }
        myscript.ws.api.Stroke apiStroke = new myscript.ws.api.Stroke(apiPoints);
        return apiStroke;
    }
    @Override
    public void recognitionResult(String recognized) {

    }
}
