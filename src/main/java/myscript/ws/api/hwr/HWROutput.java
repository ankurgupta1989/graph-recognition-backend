package myscript.ws.api.hwr;
/**
 * MyScript Cloud Sample
 */
public class HWROutput {
	public String instanceId;
	public HWRResult result;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public HWRResult getResult() {
		return result;
	}

	public void setResult(HWRResult result) {
		this.result = result;
	}

}
