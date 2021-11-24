/**
 * 
 */
package object_models.panels;

import java.util.Optional;

import controls.Control;
import controls.ControlName;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public interface JsPanelControl {
	Optional<Control> getControl(ControlName cntrlName);
}
