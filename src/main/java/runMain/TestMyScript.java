package runMain;

import myscript.ws.MyScriptCloud;
import myscript.ws.RecognitionListener;
import myscript.ws.api.Point;
import myscript.ws.api.Stroke;

import java.util.Scanner;

/**
 * Created by ankurgupta on 3/14/15.
 */
public class TestMyScript implements RecognitionListener {

    static final String apiKey = "807538b7-e805-4258-8c62-0b7b8227af1c";
    static final String recognitionCloudURL = "http://cloud.myscript.com/api/myscript/v2.0/shape/doSimpleRecognition.json";

    private MyScriptCloud hwr;
    
    public static void main(String[] argv) {
        TestMyScript testMyScript = new TestMyScript();  
        Scanner in = new Scanner(System.in);
        int numStrokes = in.nextInt();
        for (int i = 0; i < numStrokes; i++) {
            int numPoints = in.nextInt();
            Point[] points = new Point[numPoints];
            for (int j = 0; j < numPoints; j++) {
                float x = in.nextInt();
                float y = in.nextInt();
                points[j] = new Point(x, y);
            }
            Stroke stroke = new Stroke(points);
            testMyScript.recognizeStroke(stroke);
        }
        
    }
    
    public TestMyScript() {
        this.hwr = new MyScriptCloud(recognitionCloudURL, apiKey);
        hwr.setListener(this);
    }
    
    public void recognizeStroke(Stroke stroke) {
        hwr.addShapeStroke(stroke);
    }

    @Override
    public void recognitionResult(String recognized) {
        System.out.println(recognized);
    }
}
