/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import java.util.Optional;

import object_models.forms.ContainerAction;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author SteveBrown
 * @since 1.0
 * @version 1.1
 *
 */
public interface NavBarElementGetter {
	/*
	 * Load the nav-bar element using context manager.
	 */
	Optional<ContainerAction> clickAndLoad(Class<?> clazz);
	/*
	 * Get the nav-bar element. The element is NOT added to the CM.
	 */
	Optional<NavBarElement> getNavBarElement(String elementName);
}
