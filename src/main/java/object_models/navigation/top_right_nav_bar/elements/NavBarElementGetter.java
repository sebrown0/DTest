/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import java.util.Optional;

import object_models.navigation.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public interface NavBarElementGetter {
	Optional<NavBarElement> getNavBarElement(String elementName);
}
