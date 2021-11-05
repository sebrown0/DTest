/**
 * 
 */
package control_builder;

import controls.ControlName;

/**
 * @author Steve Brown
 *
 * Object to hold a control's data.
 * Passed to ControlBuilder so the contents
 * can be added to the map of controls.
 */
public class ControlData {
	private ControlName cntrlName;
	private ControlGetter controlGetter;
	
	public ControlData(ControlName cntrlName, ControlGetter controlGetter) {
		this.cntrlName = cntrlName;
		this.controlGetter = controlGetter;
	}
	
	public ControlName getCntrlName() {
		return cntrlName;
	}
	public ControlGetter getControlGetter() {
		return controlGetter;
	}
		
}
