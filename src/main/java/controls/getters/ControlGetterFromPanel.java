/**
 * 
 */
package controls.getters;

import java.util.Optional;

import control_builder.PageControl;
import controls.interfaces.Control;
import dynamic_tests.elements.ControlGroup;
import logging.WriteErrorToLog;
import object_models.panels.JsPanel;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get a control (R) from the given panel.
 */
public class ControlGetterFromPanel <R extends Control> implements ControlGetter<R>{
	private JsPanel panel;
	private String prntName;
	private String cntrlName;
	
	private R cntrl;
	
	public ControlGetterFromPanel(JsPanel panel, String prntName, String cntrlName) {
		this.panel = panel;
		this.prntName = prntName;
		this.cntrlName = cntrlName;
	}

	public Optional<R> getControl() {		
		getPanelControl()
			.getControl(prntName)
			.ifPresent(grp -> getControl((ControlGroup) grp));		
		
		return Optional.ofNullable(cntrl);
	}
	
	private PageControl getPanelControl() {
		return panel.getPanelControl();
	}

	@SuppressWarnings("unchecked")
	private void getControl(ControlGroup grp) {
		grp.getControlByTitle(cntrlName)
			.ifPresentOrElse(
				c -> cntrl = (R) c, 
				new WriteErrorToLog(
						String.format("Could not get control [%s]", cntrlName), 
						this.getClass())
		);
	}
	
}
