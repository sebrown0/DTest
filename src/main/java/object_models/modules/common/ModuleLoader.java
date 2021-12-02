/**
 * 
 */
package object_models.modules.common;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.CallingState;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateModule;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import object_models.pages.homepage.loader.HomePageElements;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 * Loads a module using the supplied ModuleElements.
 */
public class ModuleLoader {
	private Logger logger;
	private HomePageElements moduleElements;
	private String moduleName;	
	private WebDriver driver;	
	private CoreData coreData;	
	private ContextManager contextManager;
	private HomePage hp;
	
	public ModuleLoader(CoreData coreData, HomePageElements moduleElements) {
		this.coreData = coreData;
		this.logger = coreData.getLogger();
		this.moduleElements = moduleElements;
		this.driver = coreData.getWebDriver();
		this.contextManager = coreData.getContextManager();
		
		setInitialStateOfContextManager();
		setModuleName();
		checkDriver();
	}

	private void setInitialStateOfContextManager() {
		logger.debug("Setting initial state of Context Manager");
		contextManager.setLatestCallingState(new CallingState() {			
			@Override
			public State getState(ContextState context) {
				return new StateModule(context, driver);
			}
		});
		contextManager.setFirstContext(moduleElements.getContextForModule());		
	}

	private void setModuleName() {
		moduleName = moduleElements.getModuleName();
	}
	
	private void checkDriver() {
		if (driver == null) {	logger.error("Null driver"); }
	}
	
	public boolean loadModule(String modName) {
		if(!ModuleChecker.isCurrentModule(modName, driver)){
			moduleElements.getQuickLinkToLoadModule().clickMe();
			return true;
		}	
		return false;
	}	
//	public HomePage loadModule(String modName) {
//		if(!ModuleChecker.isCurrentModule(modName, driver)){
//			moduleElements.getQuickLinkToLoadModule().clickMe();				
//		}	
//		return load();
//	}	
//	public HomePage loadModuleAndCompany(String modName, Company company) {
//		if(!ModuleChecker.isCurrentModule(modName, driver)){
//			moduleElements.getQuickLinkToLoadModule().clickMe();				
//		}	
//		return load();
//	}	
//	private HomePage load() {
//		hp = moduleElements.getHomePage();		
//		hp.setLeftNavBar(getLeftNavBar());
//		hp.setTopRightNavBar(getTopRightNavBar());
//		hp.setLeftMenu(getLeftMenu());
//		return hp;
//	}
	
//	private LeftNavBar getLeftNavBar() {
//		logger.info("Creating left nav-bar for " + moduleName + " module");
//		return new LeftNavBar(driver, contextManager);
//	}
//	
//	private TopRightNavBar getTopRightNavBar() {
//		logger.info("Creating top-right nav-bar for " + moduleName + " module");
//		
//		TopRightNavBar topRightNavBar = new TopRightNavBar(driver, contextManager);		
//		topRightNavBar.loadElements(moduleElements.getElementStrategy(contextManager));
//		return topRightNavBar;
//	}
//	
//	private LeftMenu getLeftMenu() {
//		logger.info("Creating left menu for " + moduleName + " module");
//		
//		LeftMenu leftMenu = new LeftMenu(coreData);				
//		moduleElements.setLeftMenuElements(leftMenu);
//		return leftMenu;
//	}
		
}
