/**
 * 
 */
package controls.adders;

import java.util.List;

import control_builder.control_getters.ControlGetter;
import control_data.ControlData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface ControlAdder {
	void addElement(ControlGetter controlGetter, List<ControlData> controlData);
}
