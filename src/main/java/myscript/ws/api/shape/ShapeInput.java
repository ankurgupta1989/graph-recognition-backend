package myscript.ws.api.shape;

import myscript.ws.api.hwr.HWRComponent;

import java.util.List;

/**
 * Created by ankurgupta on 3/14/15.
 */
public class ShapeInput {
    public List<HWRComponent> components;

    public List<HWRComponent> getComponents() {
        return components;
    }

    public void setComponents(List<HWRComponent> components) {
        this.components = components;
    }
    
    
}
