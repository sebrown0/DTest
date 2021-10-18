/**
 * 
 */
package object_models.modules.common;

import context_manager.ContextManager;
import context_manager.contexts.Context;
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
	Context getContextForModule(ContextManager contextManager);
	NavBarElementStrategy getElementStrategy(ContextManager contextManager);
	QuickLink getQuickLinkToLoadModule();
	void setLeftMenuElements(LeftMenu menu);
	String getModuleName();	
}
