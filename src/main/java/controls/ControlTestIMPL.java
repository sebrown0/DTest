/**
 * 
 */
package controls;

import java.util.Optional;

import object_models.panels.JsPanel;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlTestIMPL {
	private String controlName;
	
	public ControlTestIMPL(String controlName) {
		this.controlName = controlName;
	}
	
	public String getFaFaText(JsPanel jsPanel) {		
		String faFaText = null;
		Optional<Control> cntrl = jsPanel.getControl(controlName);
		if(cntrl.isPresent()) {
			Object o = cntrl.get();
			if(o instanceof HasFaFa) {
				HasFaFa faFa = (HasFaFa) o;
				faFaText = faFa.getFaFaText();
			}			
		}	
		return faFaText;
	}
	public String getControlText(JsPanel jsPanel) {		
		String cntrlText = null;
		Optional<Control> cntrl = jsPanel.getControl(controlName);
		if(cntrl.isPresent()) {
			Object o = cntrl.get();
			if(o instanceof DisplayedText) {
				DisplayedText displayedText = (DisplayedText) o;
				cntrlText = displayedText.getText();
			}			
		}	
		return cntrlText;
	}
}
