/**
 * 
 */
package controls;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import object_models.element.TextOut;
import object_models.employee.EmployeeSelection;

/**
 * @author Steve Brown
 *
 * All the controls that may be on a page.
 * Each page object knows the controls required
 * and they are passed via the builder.
 */
public class PageControl {
	private Map<String, ControlGetter> controls;

	public PageControl(BuildControls builder) {
		this.controls = builder.build();
	}
	
	public Optional<TextOut> getTextOut(ControlName cntrlName) {
		if(controls.containsKey(cntrlName.getName())) {
			return Optional.of((TextOut) controls.get(cntrlName.getName()).getControl());
		}else {
			writeErrorMsg("TextOut", cntrlName.getName());			
			return Optional.empty();
		}			
	}
	
	public Optional<EmployeeSelection> getEmployeeSelection(ControlName cntrlName){
		if(controls.containsKey(cntrlName.getName())) {
			return Optional.of((EmployeeSelection) controls.get(cntrlName.getName()).getControl());
		}else {
			writeErrorMsg("EmployeeSelection", cntrlName.getName());			
			return Optional.empty();
		}
	}
	
	private void writeErrorMsg(String cntrlType, String key) {
		System.out.println("Could not find control [" + cntrlType + "] for key [" + key + "]");
		LogManager.getLogger().error("Could not find control [" + cntrlType + "] for key [" + key + "]");
	}
	
}
