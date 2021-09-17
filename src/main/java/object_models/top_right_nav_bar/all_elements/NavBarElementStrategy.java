/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import java.util.Map;

import object_models.top_right_nav_bar.common.NavBarElement;
import object_models.top_right_nav_bar.quick_links.QuickLinks;

/**
 * @author SteveBrown
 *
 */
public interface NavBarElementStrategy {
	Map<String, NavBarElement> getElements();
	QuickLinks getQuickLinks();
}
