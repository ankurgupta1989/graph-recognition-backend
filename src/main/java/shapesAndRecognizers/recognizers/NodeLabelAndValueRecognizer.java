package shapesAndRecognizers.recognizers;

import myscript.ws.MyScriptCloud;
import myscript.ws.RecognitionListener;
import org.apache.commons.lang3.math.NumberUtils;
import shapesAndRecognizers.*;
import shapesAndRecognizers.shapes.NodeLabel;
import shapesAndRecognizers.shapes.NodeValue;
import shapesAndRecognizers.shapes.Rectangle;

import java.util.List;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class NodeLabelAndValueRecognizer implements Recognizer, RecognitionListener {

//    static final String apiKey = "807538b7-e805-4258-8c62-0b7b8227af1c";
//    static final String apiKey = "e757a5b5-4835-4883-9f22-40e51e69e5a3";
    static final String apiKey = "b18aed63-ed5d-44a8-bae9-e6b2245f8f3b";
    static final String recognitionCloudURL = "http://cloud.myscript.com/api/myscript/v2.0/hwr/doSimpleRecognition.json";

    public static String getRecognizedString(Stroke stroke) throws RecognitionException {
        myscript.ws.api.Stroke[] strokeArr = convertStroke(stroke);
        MyScriptCloud hwr = new MyScriptCloud(recognitionCloudURL, apiKey);
        return hwr.addStroke(strokeArr);
    }
    
    @Override
    public Shape recognize(Stroke stroke) throws RecognitionException {
        myscript.ws.api.Stroke[] strokeArr = convertStroke(stroke);
        Rectangle boundingBox = stroke.getBoundingBox();
        return getShape(boundingBox, strokeArr);
    }
    
    @Override
    public Shape recognizeList(List<Stroke> strokeList) throws RecognitionException {
        myscript.ws.api.Stroke[] apiStroke = convertStroke(strokeList);
        Rectangle boundingBox = RecognizerUtility.getBoundingBox(strokeList);
        return getShape(boundingBox, apiStroke);
    }

    private Shape getShape(Rectangle boundingBox, myscript.ws.api.Stroke[] strokeArr) {
        MyScriptCloud hwr = new MyScriptCloud(recognitionCloudURL, apiKey);
        hwr.setListener(this);
        
        String recognized = hwr.addStroke(strokeArr);
        if (! NumberUtils.isNumber(recognized)) {
            NodeLabel nodeLabel = new NodeLabel();
            nodeLabel.setLabel(recognized);
            nodeLabel.setBoundingBox(boundingBox);
            return nodeLabel;
        }
        else {
            NodeValue nodeValue = new NodeValue();
            nodeValue.setNumber(Integer.valueOf(recognized));
            nodeValue.setBoundingBox(boundingBox);
            return nodeValue;
        }
    }
    
    public static myscript.ws.api.Stroke[] convertStroke(Stroke stroke) {
        List<Point> points = stroke.getPoints();
        myscript.ws.api.Point[] apiPoints = new myscript.ws.api.Point[points.size()];
        int index = 0;
        for (Point point : points) {
            apiPoints[index++] = new myscript.ws.api.Point((float)point.getX(), (float)point.getY());
        }
        myscript.ws.api.Stroke apiStroke = new myscript.ws.api.Stroke(apiPoints);
        myscript.ws.api.Stroke[] strokeArr = new myscript.ws.api.Stroke[1];
        strokeArr[0] = apiStroke;
        return strokeArr;
    }

    public static myscript.ws.api.Stroke[] convertStroke(List<Stroke> strokeList) {

        myscript.ws.api.Stroke[] strokeArr = new myscript.ws.api.Stroke[strokeList.size()];
        int cnt = 0;
        for (Stroke stroke : strokeList) {
            List<Point> points = stroke.getPoints();
            myscript.ws.api.Point[] apiPoints = new myscript.ws.api.Point[points.size()];
            int index = 0;
            for (Point point : points) {
                apiPoints[index++] = new myscript.ws.api.Point((float) point.getX(), (float) point.getY());
            }
            myscript.ws.api.Stroke apiStroke = new myscript.ws.api.Stroke(apiPoints);
            strokeArr[cnt++] = apiStroke;
        }

        return strokeArr;
    }
    
    @Override
    public void recognitionResult(String recognized) {

    }
}
