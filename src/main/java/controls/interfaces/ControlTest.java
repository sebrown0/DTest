/**
 * 
 */
package controls.interfaces;

import java.util.Map;
import java.util.Optional;

import control_builder.control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ControlTest {
	Optional<Control> getControl(String cntrlName);
	Map<String, ControlGetter> getControls();
}
