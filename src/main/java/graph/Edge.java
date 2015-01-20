package graph;


import java.io.Serializable;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Edge implements Serializable {

    private Node toVertex;
    private Node fromVertex;
    private Integer value;
    private String externalLabel;


    public Edge(Node fromVertex, Node toVertex, Integer edgeNumber, String externalLabel) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.value = edgeNumber;
        this.externalLabel = externalLabel;
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

    public String getExternalLabel() {
        return externalLabel;
    }

    public void setExternalLabel(String externalLabel) {
        this.externalLabel = externalLabel;
    }


}