package graph;


import java.io.Serializable;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Edge implements Serializable {

    private Node toVertex;
    private Node fromVertex;
    private Integer value;
    private String label;


    public Edge(Node fromVertex, Node toVertex, Integer edgeNumber, String label) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.value = edgeNumber;
        this.label = label;
    }

    public Node getToVertex() {
        return toVertex;
    }

    public void setToVertex(Node toVertex) {
        this.toVertex = toVertex;
    }

    public Node getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Node fromVertex) {
        this.fromVertex = fromVertex;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}