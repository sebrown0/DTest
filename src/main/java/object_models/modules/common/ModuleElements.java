/**
 * 
 */
package object_models.modules.common;

import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLink;

/**
 * @author Steve Brown
 *
 * This is passed to ModuleLoader so it can load the 
 * correct elements for the module.
 *  
 */
public interface ModuleElements {
	NavBarElementStrategy getElementStrategy();
	QuickLink getQuickLinkToLoadModule();
	void setLeftMenuElements(LeftMenu menu);
	String getModuleName();	
}
