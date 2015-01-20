package graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Graph  implements Serializable {

    private Map<Node, List<Edge>> graph;

    public Graph() {
        this.graph = new HashMap<Node, List<Edge>>();
    }

    public void addEdge(Integer fromVertex, Integer fromVertexValue, String fromExternalLabel,
                        Integer toVertex, Integer toVertexValue, String toExternalLabel,
                        Integer edgeValue, String lineExternalLabel) {

        Node fV = new Node(fromVertex, fromVertexValue, fromExternalLabel);
        Node tV = new Node(toVertex, toVertexValue, toExternalLabel);

        // Add edge in one direction.
        Edge edge = new Edge(fV, tV, edgeValue, lineExternalLabel);
        List<Edge> edgeList = graph.get(fV);
        if (edgeList == null) {
            edgeList = new ArrayList<Edge>();
        }
        edgeList.add(edge);
        graph.put(fV, edgeList);

        // Add edge in other direction.
        edge = new Edge(tV, fV, edgeValue, lineExternalLabel);
        edgeList = graph.get(tV);
        if (edgeList == null) {
            edgeList = new ArrayList<Edge>();
        }
        edgeList.add(edge);
        graph.put(tV, edgeList);
    }


    public Map<Node, List<Edge>> getGraph() {
        return graph;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (! (obj instanceof Node)) {
//            return false;
//        }
//        Graph other = (Graph) obj;
//        return this.graph.equals(other.graph);
//    }

}


