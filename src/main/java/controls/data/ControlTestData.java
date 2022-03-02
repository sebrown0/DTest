/**
 * 
 */
package controls.data;

import java.util.Optional;

import controls.interfaces.Control;
import controls.interfaces.DisplayedText;
import controls.interfaces.HasFaFa;
import controls.interfaces.HasToolTip;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the data from a control so it can 
 * be used in a dynamic test.
 */
public class ControlTestData {
	
	public static String getFaFaText(Optional<Control> cntrl) {		
		String faFaText = null;
		if(cntrl.isPresent()) {
			Object o = cntrl.get();
			if(o instanceof HasFaFa) {
				HasFaFa faFa = (HasFaFa) o;
				faFaText = faFa.getFaFaText();
			}			
		}	
		return faFaText;
	}
	
	public static String getControlText(Optional<Control> cntrl) {		
		String cntrlText = null;
		if(cntrl.isPresent()) {
			Object o = cntrl.get();
			if(o instanceof DisplayedText) {
				DisplayedText displayedText = (DisplayedText) o;
				cntrlText = displayedText.getText();
			}			
		}	
		return cntrlText;
	}
	
	public static String getControlToolTip(Optional<Control> cntrl) {		
		String toolTipText = null;
		if(cntrl.isPresent()) {
			Object o = cntrl.get();
			if(o instanceof HasToolTip) {
				HasToolTip tip = (HasToolTip) o;
				toolTipText = tip.getToolTipText();
			}			
		}	
		return toolTipText;
	}
	
}
