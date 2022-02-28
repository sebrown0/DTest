/**
 * 
 */
package control_builder;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;

import control_builder.control_getters.ControlGetter;
import controls.Button;
import controls.Control;
import controls.ControlGroup;
import controls.ControlName;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Get control using control name as String.
 *  Added for Dynamic Tests.
 * @since 1.0
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
	
	public Optional<Control> getControl(ControlName cntrlName){
		String cntrlKey = cntrlName.getName();
		return getControl(cntrlKey);
	}
	public Optional<Control> getControl(String cntrlKey){
//should be group_1 not employee_list
		if(controls.containsKey(cntrlKey)) {
			return (Optional<Control>) Optional.of(controls.get(cntrlKey).getControl());
		}else {
			System.out.println("PageControl: Could not find control [" + cntrlKey + "]");
			LogManager.getLogger(PageControl.class).error("Could not find control [" + cntrlKey + "]");			
			return Optional.empty();
		}
	}
	
	public void updateControl(ControlName cntrlName, ControlGetter updatedCntrl) {
		String key = cntrlName.getName();
		if(controls.containsKey(key)) {
			controls.remove(key);
			controls.put(key, updatedCntrl);	
		}
	}

	public Map<String, ControlGetter> getControls() {
		return controls;
	}

	public ControlGetter findFirst(String prnt, String child) {
		ControlGetter controlGetter = null;
//		boolean found = false;
//		while(found == false) {
//			
//		}
		for(Entry<String, ControlGetter> entry : controls.entrySet()) {
			var v = entry.getValue();
			System.out.println("->"); // TODO - remove or log 	
		}
		
		return controlGetter;
	}
	
}
