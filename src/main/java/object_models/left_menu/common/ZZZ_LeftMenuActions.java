/**
 * 
 */
package object_models.left_menu.common;

import java.util.Optional;

import org.openqa.selenium.WebElement;

import object_models.forms.ContainerAction;
import object_models.helpers.ClassFieldGetter;

/**
 * @author Steve Brown
 *
 */
public interface ZZZ_LeftMenuActions {
	// Click a parent (top level) item to reveal children.
	ZZZ_LeftMenuActions clickParent(String prntName);
	
	// Load the page, form etc...
	Optional<ContainerAction> loadElement(ClassFieldGetter fieldGetter);
	
	// Get the menu name and parent name from the class using reflection.
	// Then return the item.
	Optional<ContainerAction> clickAndLoad(Class<?> clazz);
	
	// Get the currently active (clicked) menu item.
	// This applies to parent items only.
	WebElement getActiveMenuItem();
}
