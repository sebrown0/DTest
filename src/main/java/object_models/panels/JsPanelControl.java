/**
 * 
 */
package object_models.panels;

import java.util.Optional;

import controls.Control;
import controls.ControlName;

/**
 * @author Steve Brown
 *
 */
public interface JsPanelControl {
	Optional<Control> getControl(ControlName cntrlName);
}
