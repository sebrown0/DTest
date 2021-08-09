/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import java.util.Map;

import object_models.navigation.NavBarElement;

/**
 * @author SteveBrown
 *
 */
public interface NavBarElementStrategy {
	Map<String, NavBarElement> getElements();
}
