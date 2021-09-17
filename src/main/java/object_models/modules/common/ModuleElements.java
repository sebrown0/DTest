/**
 * 
 */
package object_models.modules.common;

import object_models.left_menu.common.LeftMenu;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.quick_links.QuickLink;

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
