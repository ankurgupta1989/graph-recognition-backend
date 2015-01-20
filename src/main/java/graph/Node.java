package graph;

import java.io.Serializable;

/**
 * Created by ankurgupta on 11/24/14.
 */
public class Node implements Serializable{

    private Integer identifier;
    private Integer value;
    private String externalLabel;

    public Node(Integer identifier) {
        this.identifier = identifier;
    }

    public Node(Integer identifier, Integer value, String externalLabel) {
        this.identifier = identifier;
        this.value = value;
        this.externalLabel = externalLabel;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
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

    @Override
    public int hashCode() {
        return this.identifier;
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
        return node.identifier.equals(this.identifier);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{")
               .append(this.getExternalLabel())
               .append(", ")
               .append(this.getValue())
               .append("}");
        return builder.toString();
    }
}
