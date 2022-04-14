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
 * @since 1.0
 */
public interface ControlAdder {
	void addElement(ControlGetter controlGetter, List<ControlData> controlData);
}
