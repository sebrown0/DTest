/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.Optional;

import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 * 
 */
public interface LeftMenuActions {
	// Click a parent (top level) item to reveal children.
	LeftMenuActions clickParent(String prntName);
	// Click item and load the page, form etc...
	Optional<ContainerAction> clickAndLoad(String elementName);
}
