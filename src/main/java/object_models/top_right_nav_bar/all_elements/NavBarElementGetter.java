/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import java.util.Optional;

import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public interface NavBarElementGetter {
	Optional<NavBarElement> getNavBarElement(String elementName);
}
