package graph;

import java.io.Serializable;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Node implements Serializable{

    private Integer value;
    private String label;

    public Node(Integer value, String label) {
        this.value = value;
        this.label = label;
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

    @Override
    public int hashCode() {
        return this.label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Node)) {
            return false;
        }
        Node node = (Node) obj;
        return node.label.equals(this.label);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{")
               .append(this.getLabel())
               .append(", ")
               .append(this.getValue())
               .append("}");
        return builder.toString();
    }
}
