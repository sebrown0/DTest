/**
 * 
 */
package object_models.modules.personnel;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import context_manager.Context;
import context_manager.ContextManager;
import object_models.left_menu.common.LeftMenu;
import object_models.modules.common.ModuleElements;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.personnel.NavBarPersonnelElements;
import object_models.top_right_nav_bar.quick_links.QuickLink;
import object_models.top_right_nav_bar.quick_links.QuickLinkPersonnel;
import providers.ModuleNames;


/**
 * @author Steve Brown
 *
 */
public class PersonnelModuleLoader implements ModuleElements {
	private WebDriver driver;
//	private ContextManager contextManager;
	
	public PersonnelModuleLoader(WebDriver driver) {
		this.driver = driver;		
	}

//	public void setContextManager(ContextManager contextManager) {
//		this.contextManager = contextManager;
//	}

	@Override
	public NavBarElementStrategy getElementStrategy(ContextManager contextManager) {
		return new NavBarPersonnelElements(driver, contextManager);
	}

	@Override
	public QuickLink getQuickLinkToLoadModule() {
		return new QuickLinkPersonnel(driver);
	}

	@Override
	public String getModuleName() {
		return ModuleNames.PERSONNEL_NAME;
	}

	@Override
	public void setLeftMenuElements(LeftMenu menu) {
		LogManager.getLogger().error("Not implemented");
		
	}

	@Override
	public Context getContextForModule(ContextManager contextManager) {
		System.out.println("NOT IMPLEMENTED!!"); // TODO - remove or log 	
		LogManager.getLogger().error("NOT IMPLEMENTED!!");
		// TODO - IMPLEMENT!!
		return null;
	}
}
