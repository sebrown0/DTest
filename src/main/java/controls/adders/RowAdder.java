/**
 * 
 */
package controls.adders;

import java.util.List;

import control_data.ControlData;
import control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class RowAdder implements ControlAdder {
	@Override
	public void addElement(ControlGetter controlGetter, List<ControlData> controlData) {
		controlData.add(new ControlData(controlGetter));
	}	
}
