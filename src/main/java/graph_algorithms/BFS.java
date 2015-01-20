package graph_algorithms;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.*;

/**
 * Created by ankurgupta on 12/13/14.
 */
public class BFS implements RunAlgorithm<String, String> {

    @Override
    public void run(Graph graph, Map<String, String> map, StringBuilder output) {

        Map<Node, List<Edge>> G = graph.getGraph();

        String sourceVertex = map.get("source");
        Node startNode = null;
        for (Node node : G.keySet()) {
            if (sourceVertex.equals(node.getExternalLabel())) {
                startNode = node;
                break;
            }
        }

        Queue<Node> queue = new LinkedList<Node>();
        Set<Node> used = new HashSet<Node>();

        queue.add(startNode);
        used.add(startNode);
        assert startNode != null;
//        output.append(startNode.toString());
//        output.append("\n");

        while (! queue.isEmpty()) {
            Node cur = queue.poll();

            List<Edge> edgeList = G.get(cur);

            for (Edge edge : edgeList) {
                Node toVertex = edge.getToVertex();
                if (! used.contains(toVertex)) {
                    queue.add(toVertex);
                    used.add(toVertex);
                    output.append(cur.toString() + " ----> " + toVertex.toString() + " via " + "{" + edge.getValue() + ", " + edge.getExternalLabel() + "}" );
                    output.append("\n");
                }
            }
        }

    }
}
