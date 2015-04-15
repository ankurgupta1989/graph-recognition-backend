package graphAlgorithms;

import graph.Edge;
import graph.Graph;
import graph.Node;
import graphAlgorithms.datastructures.UnionFind;
import graphAlgorithms.datastructures.UnionFindImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by ankurgupta on 4/12/15.
 */
public class Kruskal implements RunAlgorithm<String, String> {
    @Override
    public void run(Graph graph, Map<String, String> map, StringBuilder output, List<Edge> outEdgeList) {
        UnionFind store = new UnionFindImplementation(1000);
        Map<Node, List<Edge>> M = graph.getGraph();
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
        for (List<Edge> edgeList : M.values()) {
            for (Edge edge : edgeList) {
                queue.add(edge);
            }
        }
        
        Map<Node, Integer> identifier = new HashMap<Node, Integer>();
        int cnt = 0;
        for (Node node: M.keySet()) {
            identifier.put(node, cnt++);
        }
        
        int totalCost = 0;
        while (! queue.isEmpty()) {
            Edge edge = queue.poll();
            Integer g1 = store.find(identifier.get(edge.getFromVertex()));
            Integer g2 = store.find(identifier.get(edge.getToVertex()));
            if (! g1.equals(g2)) {
                store.unify(g1, g2);
                outEdgeList.add(edge);
                totalCost += edge.getValue();
            }
        }
        output.append("Cost of minimum spanning tree is " + Integer.toString(totalCost));
    }
}
