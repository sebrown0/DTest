/**
 * 
 */
package controls.adders;

import java.util.List;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TabGroupAdder implements ControlAdder {	
	@Override
	public void addElement(ControlGetter controlGetter, List<ControlData> controlData) {
		controlData.add(new ControlData(controlGetter));
	}
}
