/**
 * 
 */
package object_models.helpers;

import java.util.List;

import control_builder.ControlData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Controls that are on a page or form.
 * This interface can be used to split controls
 * on the relevant page/form. 
 * 
 * For example into common controls & specific controls.
 */
public interface ElementControls {
	List<ControlData> getControls();
}
