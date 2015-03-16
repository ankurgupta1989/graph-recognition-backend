package myscript.ws.api.hwr;
/**
 * MyScript Cloud Sample
 */
import java.util.List;

public class HWRCandidate {
	public String label;
	public float normalizedScore;
	public float resemblanceScore;
	public List<HWRChild> children;
	public int childrenSize;
    public String childs;
    public String technicalMessage;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getNormalizedScore() {
		return normalizedScore;
	}

	public void setNormalizedScore(float normalizedScore) {
		this.normalizedScore = normalizedScore;
	}

	public float getResemblanceScore() {
		return resemblanceScore;
	}

	public void setResemblanceScore(float resemblanceScore) {
		this.resemblanceScore = resemblanceScore;
	}

	public List<HWRChild> getChildren() {
		return children;
	}

	public void setChildren(List<HWRChild> children) {
		this.children = children;
	}

	public int getChildrenSize() {
		return childrenSize;
	}

	public void setChildrenSize(int childrenSize) {
		this.childrenSize = childrenSize;
	}

    public String getChilds() {
        return childs;
    }

    public void setChilds(String childs) {
        this.childs = childs;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

}
