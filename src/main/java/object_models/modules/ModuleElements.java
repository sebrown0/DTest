/**
 * 
 */
package object_models.modules;

import exceptions.ElementDoesNotExistException;
import object_models.navigation.left_side_menu.ZZZ_LeftMenu;
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
	ZZZ_LeftMenu getLeftMenu() throws ElementDoesNotExistException;
	String getModuleName();	
}
