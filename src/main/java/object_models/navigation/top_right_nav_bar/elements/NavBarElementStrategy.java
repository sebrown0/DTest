/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import java.util.Map;

import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.nav_bar_clicker.NavBarClicker;

/**
 * @author SteveBrown
 *
 */
public interface NavBarElementStrategy {
	NavBarClicker getNavBarClicker();
	Map<String, NavBarElement> getElements();
}
