/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import java.util.Map;

import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;
import object_models.navigation.top_right_nav_bar.xx_nav_bar_clicker.XX_NavBarClicker;

/**
 * @author SteveBrown
 *
 */
public interface NavBarElementStrategy {
	XX_NavBarClicker getNavBarClicker();
	Map<String, NavBarElement> getElements();
	QuickLinks getQuickLinks();
}
