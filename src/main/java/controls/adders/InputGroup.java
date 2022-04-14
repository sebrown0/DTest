/**
 * 
 */
package controls.adders;

import java.util.List;

import org.openqa.selenium.By;

import control_data.ControlData;
import control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class InputGroup implements ControlAdder {
	private By findBy;
	
	public InputGroup(By findBy) {
		this.findBy = findBy;
	}

	@Override
	public void addElement(ControlGetter controlGetter, List<ControlData> controlData) {
		controlData.add(
			new ControlData(
					controlGetter
							.setParent(findBy)));
	}	
		
}

