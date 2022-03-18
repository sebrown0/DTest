/**
 * 
 */
package object_models.pages.homepage.loader;

import context_manager.ContextManager;
import context_manager.contexts.Context;
import entities.company.Company;
import object_models.left_menu.common.LeftMenu;
import object_models.modules.common.nav.nav_bar_elements.NavBarElementStrategy;
import object_models.modules.common.nav.quick_links.QuickLink;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add setCoreData(...)
 * @version 1.2
 * 	Renamed 
 * @since 1.0
 *
 * This is passed to ModuleLoader so it can load the 
 * correct elements for the module.
 *  
 */
public interface HomePageElements {
	void setCoreData(CoreData coreData);
	void setLeftMenuElements(LeftMenu menu);
	
	HomePage getHomePage();
	Context getContextForModule();
	NavBarElementStrategy getElementStrategy(ContextManager contextManager);
	QuickLink getQuickLinkToLoadModule();	
	String getModuleName();	
	Company getCompany();
}
