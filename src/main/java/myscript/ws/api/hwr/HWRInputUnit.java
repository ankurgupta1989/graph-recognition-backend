package myscript.ws.api.hwr;
/**
 * MyScript Cloud Sample
 */
import java.util.ArrayList;
import java.util.List;

public class HWRInputUnit {
	public String hwrInputType;
	public List<HWRComponent> components;

	public HWRInputUnit() {
		this.hwrInputType = "MULTI_LINE_TEXT";
		this.components = new ArrayList<HWRComponent>();
	}

	public String getHwrInputType() {
		return hwrInputType;
	}

	public void setHwrInputType(String hwrInputType) {
		this.hwrInputType = hwrInputType;
	}

	public List<HWRComponent> getComponents() {
		return components;
	}

	public void setComponents(List<HWRComponent> components) {
		this.components = components;
	}

}
