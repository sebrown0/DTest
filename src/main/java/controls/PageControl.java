/**
 * 
 */
package controls;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import object_models.element.TextOut;

/**
 * @author Steve Brown
 *
 * All the controls that may be on a page.
 * Each page object knows the controls required
 * and they are passed via the builder.
 */
public class PageControl {
	private Map<String, Control> controls;

	public PageControl(BuildControls builder) {
		this.controls = builder.build();
	}
	
	public Optional<TextOut> getTextOut(ControlName cntrlName) {
		if(controls.containsKey(cntrlName.getName())) {
			return Optional.of((TextOut) controls.get(cntrlName.getName()));
		}else {
			writeErrorMsg("TextOut", cntrlName.getName());			
			return Optional.empty();
		}			
	}
	
	private void writeErrorMsg(String cntrlType, String key) {
		System.out.println("Could not find control [" + cntrlType + "] for key [" + key + "]");
		LogManager.getLogger().error("Could not find control [" + cntrlType + "] for key [" + key + "]");
	}
	
}
