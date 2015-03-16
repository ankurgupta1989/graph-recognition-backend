package myscript.ws.api.hwr;
/**
 * MyScript Cloud Sample
 */
public class HWRParameter {
	public String resultDetail;
	public HWRProperties hwrProperties;
	public String language;

	public HWRParameter() {
		this.resultDetail = "TEXT";
		this.language = "en_US";
	}

	public String getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}

	public HWRProperties getHwrProperties() {
		return hwrProperties;
	}

	public void setHwrProperties(HWRProperties hwrProperties) {
		this.hwrProperties = hwrProperties;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}