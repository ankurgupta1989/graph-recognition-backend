package graph_algorithms;

import graph.Graph;

import java.util.Map;

/**
 * Created by ankurgupta on 12/13/14.
 */
public interface RunAlgorithm <K, V> {
    public void run(Graph graph, Map<K, V> map, StringBuilder output);
}
