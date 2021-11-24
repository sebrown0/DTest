/**
 * 
 */
package control_builder;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import controls.Control;
import controls.ControlName;

/**
 * @author SteveBrown
 * @version 1.0
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
		if(controls.containsKey(cntrlKey)) {
			return (Optional<Control>) Optional.of(controls.get(cntrlKey).getControl());
		}else {
//			System.out.println("Could not find control [" + cntrlKey + "]");
			LogManager.getLogger().error("Could not find control [" + cntrlKey + "]");			
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
	
}
