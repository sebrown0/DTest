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
	private String cntrlName;
	private ControlGetter controlGetter;
	
	/*
	 * TODO - put an enum in the XML that matches ControlName, or
	 * make sure that we have ONE place for getting the control names!
	 */
	public ControlData(String cntrlName, ControlGetter controlGetter) {
		this.cntrlName = cntrlName;
		this.controlGetter = controlGetter;
	}
	
	public ControlData(ControlName cntrlName, ControlGetter controlGetter) {
		this.cntrlName = cntrlName.getName();
		this.controlGetter = controlGetter;
	}
	
	public String getCntrlName() {
		return cntrlName;
	}
	public ControlGetter getControlGetter() {
		return controlGetter;
	}
		
}
