/**
 * 
 */
package controls;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve Brown
 *
 * Add a control to the map with the control name as the key.
 * This is then passed to page control as [BuildControls].
 * Where the control is 'built'.
 */
public class ControlBuilder implements BuildControls {
	private Map<String, ControlGetter> controls = new HashMap<>();
		
	public ControlBuilder addControl(ControlName cntrlName, ControlGetter controlGetter) {
		controls.put(cntrlName.getName(), controlGetter);
		return this;
	}
		
	@Override
	public Map<String, ControlGetter> build(){
		return controls;
	}
}
