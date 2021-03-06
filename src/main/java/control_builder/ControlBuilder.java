/**
 * 
 */
package control_builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Add a control to the map with the control name as the key.
 * This is then passed to page control as [BuildControls].
 * Where the control is 'built'.
 */
public class ControlBuilder implements BuildControls {
	private Map<String, ControlGetter> controls = new HashMap<>();
	
	public ControlBuilder addControls(List<ControlData> controlDataList) {
		for (ControlData cntrl : controlDataList) {
			addControl(cntrl.getControlGetter());
		}
		return this;
	}

	public ControlBuilder addControl(ControlGetter controlGetter) {
		controls.put(controlGetter.getName(), controlGetter);
		return this;
	}
		
	@Override
	public Map<String, ControlGetter> build(){
		return controls;
	}
}
