package myscript.ws.api.hwr;
/**
 * MyScript Cloud Sample
 */
import java.util.ArrayList;
import java.util.List;

public class HWRInput {
	public HWRParameter hwrParameter;
	public List<HWRInputUnit> inputUnits;

	public HWRInput() {
		this.hwrParameter = new HWRParameter();
		this.hwrParameter.hwrProperties = new HWRProperties();
		this.inputUnits = new ArrayList<HWRInputUnit>();
		this.inputUnits.add(new HWRInputUnit());
	}

	public void addComponent() {
		int lastIndex = this.inputUnits.get(0).components.size() - 1;
		if (this.inputUnits.get(0).components.size() > 0 && this.inputUnits.get(0).components.get(lastIndex).x.size() < 1)
			return;
		this.inputUnits.get(0).components.add(new HWRComponent());
	}

	public void addComponentPoint(float x, float y) {
		if (this.inputUnits.get(0).components.size() < 1)
			this.addComponent();
		this.inputUnits.get(0).components.get(this.inputUnits.get(0).components .size() - 1).x.add(x);
		this.inputUnits.get(0).components.get(this.inputUnits.get(0).components	.size() - 1).y.add(y);
	}

	public void clearComponents() {
		this.inputUnits.get(0).components.clear();
	}

	public HWRParameter getHwrParameter() {
		return hwrParameter;
	}

	public void setHwrParameter(HWRParameter hwrParameter) {
		this.hwrParameter = hwrParameter;
	}

	public List<HWRInputUnit> getInputUnits() {
		return inputUnits;
	}

	public void setInputUnits(List<HWRInputUnit> inputUnits) {
		this.inputUnits = inputUnits;
	}

}
