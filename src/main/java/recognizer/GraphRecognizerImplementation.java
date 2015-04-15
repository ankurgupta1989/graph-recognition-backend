package recognizer;

import graph.Graph;
import shapesAndRecognizers.RecognitionException;
import shapesAndRecognizers.Recognizer;
import shapesAndRecognizers.Shape;
import shapesAndRecognizers.Stroke;
import shapesAndRecognizers.recognizers.CircleRecognizer;
import shapesAndRecognizers.recognizers.LineRecognizer;
import shapesAndRecognizers.recognizers.NodeLabelAndValueRecognizer;
import shapesAndRecognizers.shapes.Arrow;
import shapesAndRecognizers.shapes.Circle;
import shapesAndRecognizers.shapes.Line;

import java.util.*;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class GraphRecognizerImplementation implements GraphRecognizerInterface {

    private static List<Recognizer> recognizerList;
    
    private static long TIME_STAMP_CUTOFF;
    private static long DISTANCE_CUTOFF;
    private static long DELETE_DISTANCE_CUTOFF;
    
    static {
        recognizerList = new ArrayList<Recognizer>();
        recognizerList.add(new LineRecognizer());
        recognizerList.add(new CircleRecognizer());
//        recognizerList.add(new ArrowRecognizer());
        recognizerList.add(new NodeLabelAndValueRecognizer());
        TIME_STAMP_CUTOFF = 4000;
        DISTANCE_CUTOFF = 25;
        DELETE_DISTANCE_CUTOFF = 10;
    }
    
    private Map<Integer, Shape> shapeMap;
    
    private List<Stroke> strokeList;
    
    private Map<Integer, Long> strokeTiming;
    
    private Graph graph;
    
    public GraphRecognizerImplementation() {
        this.shapeMap = new HashMap<Integer, Shape>();
        this.strokeList = new ArrayList<Stroke>();
        this.strokeTiming = new HashMap<Integer, Long>();
        this.graph = null;
    }
    
    private boolean isShape(Shape shape) {
        if ( (shape instanceof Line) || (shape instanceof Circle) || (shape instanceof Arrow)) {
            return true;
        }
        return false;
    }
    
    private void removeNearbyStroke(Stroke stroke) {
        Iterator<Stroke> it = strokeList.iterator();
        while (it.hasNext()) {
            Stroke preStroke = it.next();
            if (stroke.distance(preStroke) < DELETE_DISTANCE_CUTOFF) {
                it.remove();
                if (strokeTiming.containsKey(preStroke.getIdentifier())) {
                    strokeTiming.remove(preStroke.getIdentifier());
                }
                if (shapeMap.containsKey(preStroke.getIdentifier())) {
                    shapeMap.remove(preStroke.getIdentifier());
                }
            }
        }
    }
    
    @Override
    public void classifyStroke(Stroke stroke) throws RecognitionException {
        
        String recognizedString = NodeLabelAndValueRecognizer.getRecognizedString(stroke);
        if ("Z".equals(recognizedString) || "z".equals(recognizedString)) {
            removeNearbyStroke(stroke);
            return;
        }
        
        Shape lineShape = null;
        try {
            LineRecognizer lineRecognizer = new LineRecognizer();
            lineShape = lineRecognizer.recognize(stroke);
        } catch (RecognitionException e) {
            // Do nothing.
        }
        
        
        List<Stroke> strokeListToRecognize = new ArrayList<Stroke>();
        Integer commonId = null;
        for (Stroke preStroke : strokeList) {
            if (stroke.getTimestamp() - preStroke.getTimestamp() < TIME_STAMP_CUTOFF) {
                double dist = stroke.distance(preStroke);
                if (dist >= DISTANCE_CUTOFF) {
                    continue;
                }
                if (lineShape != null && stroke.getBoundingBox().biggerDimension() > 2*preStroke.getBoundingBox().biggerDimension()) {
                    continue;
                }
                if (shapeMap.containsKey(preStroke.getIdentifier())) {
                    shapeMap.remove(preStroke.getIdentifier());
                }
                commonId = preStroke.getIdentifier();
                break;
            }
        }
        
        for (Stroke preStroke : strokeList) {
            if (commonId != null && preStroke.getIdentifier().equals(commonId)) {
                strokeListToRecognize.add(preStroke);
            }
        }
        strokeListToRecognize.add(stroke);
        
        Shape shape = null;
        for (Recognizer recognizer : recognizerList) {
            try {
                if (strokeListToRecognize.size() == 1) {
                    shape = recognizer.recognize(stroke);
                }
                else {
                    shape = recognizer.recognizeList(strokeListToRecognize);
                }
                break;
            }
            catch(RecognitionException e) {
                // ok try the next one;
            }
        }
        if (shape == null) {
            throw new RecognitionException();
        }

        Integer id = new Random().nextInt();
        if (! isShape(shape)) {
            strokeList.add(stroke);
            this.strokeTiming.put(id, stroke.getTimestamp());
            for (Stroke otherStroke : strokeListToRecognize) {
                if (strokeTiming.containsKey(otherStroke.getIdentifier())) {
                    strokeTiming.remove(otherStroke.getIdentifier());
                }
                otherStroke.setIdentifier(id);
            }
        }
        
        this.shapeMap.put(id, shape);
    }

    @Override
    public Collection<Shape> getAllShapes() {
        return this.shapeMap.values();
    }

    @Override
    public Collection<Stroke> getAllStrokes() {
        for (Integer id : this.strokeTiming.keySet()) {
            long curTime = new Date().getTime();
            if (curTime - this.strokeTiming.get(id) > TIME_STAMP_CUTOFF) {
                Iterator<Stroke> it = strokeList.iterator();
                while (it.hasNext()) {
                    Stroke temp = it.next();
                    if (temp.getIdentifier().equals(id)) {
                        it.remove();
                    }
                }
            }
        }
        return this.strokeList;
    }

    @Override
    public void finish() throws RecognitionException {
        Collection<Shape> shapeCollection = this.shapeMap.values();
        List<Shape> shapeList = new ArrayList<Shape>();
        for (Shape shape : shapeCollection) {
            shapeList.add(shape);
        }
        this.graph = RecognitionUtility.recognizeGraph(shapeList);
        this.strokeList = new ArrayList<Stroke>();
        this.strokeTiming = new HashMap<Integer, Long>();
    }


    @Override
    public Graph getGraph() {
        return this.graph;
    }
}
