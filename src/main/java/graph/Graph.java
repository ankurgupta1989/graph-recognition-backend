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
    private Map<String, Node> nodeIdentifier;

    public Graph() {
        this.graph = new HashMap<Node, List<Edge>>();
        this.nodeIdentifier = new HashMap<String, Node>();
    }

    public void addNode(String label, Integer value) {
        Node node = new Node(value, label);
        List<Edge> edgeList = new ArrayList<Edge>();
        graph.put(node, edgeList);
        nodeIdentifier.put(label, node);
    }
    
    public void addUndirectedEdge(String fromLabel, String toLabel, String edgeLabel, Integer edgeValue) {
        Node fromVertex = nodeIdentifier.get(fromLabel);
        Node toVertex = nodeIdentifier.get(toLabel);
        List<Edge> fromVertexEdgeList = graph.get(fromVertex);
        List<Edge> toVertexEdgeList = graph.get(toVertex);
        
        // Add edge in one direction.
        Edge edge = new Edge(fromVertex, toVertex, edgeValue, edgeLabel);
        fromVertexEdgeList.add(edge);

        // Add edge in other direction.
        edge = new Edge(toVertex, fromVertex, edgeValue, edgeLabel);
        toVertexEdgeList.add(edge);
    }

    public void addDirectedEdge(String fromLabel, String toLabel, String edgeLabel, Integer edgeValue) {
        Node fromVertex = nodeIdentifier.get(fromLabel);
        Node toVertex = nodeIdentifier.get(toLabel);
        List<Edge> fromVertexEdgeList = graph.get(fromVertex);
        List<Edge> toVertexEdgeList = graph.get(toVertex);

        // Add edge in one direction.
        Edge edge = new Edge(fromVertex, toVertex, edgeValue, edgeLabel);
        fromVertexEdgeList.add(edge);
    }

    public Map<Node, List<Edge>> getGraph() {
        return graph;
    }


}


