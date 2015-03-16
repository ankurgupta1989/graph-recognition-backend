package service.input;

import java.util.Map;

/**
 * Created by ankurgupta on 3/4/15.
 */
public class RunAlgorithmRequest {
    private String algorithmName;

    private Map<String, String> arguments;
    
    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
