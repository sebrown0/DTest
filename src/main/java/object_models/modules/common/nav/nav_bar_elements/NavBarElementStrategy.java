/**
 * 
 */
package object_models.modules.common.nav.nav_bar_elements;

import java.util.Map;

import object_models.modules.common.nav.quick_links.QuickLinks;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author SteveBrown
 *
 */
public interface NavBarElementStrategy {
	Map<String, NavBarElement> getElements();
	QuickLinks getQuickLinks();
}
