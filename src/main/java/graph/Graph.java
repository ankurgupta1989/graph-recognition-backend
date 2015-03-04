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

    public void addEdge(Integer fromVertex, Integer fromVertexValue, String fromVertexLabel,
                        Integer toVertex, Integer toVertexValue, String toVertexLabel,
                        Integer edgeValue, String edgeLabel) {

        Node fV = new Node(fromVertex, fromVertexValue, fromVertexLabel);
        Node tV = new Node(toVertex, toVertexValue, toVertexLabel);

        // Add edge in one direction.
        Edge edge = new Edge(fV, tV, edgeValue, edgeLabel);
        List<Edge> edgeList = graph.get(fV);
        if (edgeList == null) {
            edgeList = new ArrayList<Edge>();
        }
        edgeList.add(edge);
        graph.put(fV, edgeList);

        // Add edge in other direction.
        edge = new Edge(tV, fV, edgeValue, edgeLabel);
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


}


