/**
 * 
 */
package object_models.modules.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.top_right_nav_bar.common.TopRightNavBar;

/**
 * @author Steve Brown
 *
 * Loads a module using the supplied ModuleElements.
 */
public class ModuleLoader {
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	private ModuleElements moduleElements;
	private String moduleName;
	private LeftNavBar leftNavBar;
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;	
		
	public ModuleLoader(WebDriver driver, ModuleElements moduleElements) {		
		this.moduleElements = moduleElements;
		this.driver = driver;
	
		setModuleName();
		checkDriver();
		loadModule();
	}

	private void setModuleName() {
		moduleName = moduleElements.getModuleName();
	}
	
	private void checkDriver() {
		if (driver == null) {	logger.error("Null driver"); }
	}
		
	private void loadModule() {
		if(!ModuleChecker.getCurrentModule(driver).equalsIgnoreCase(moduleName)){			
			logger.info(moduleName + " module not loaded. Loading now");
			moduleElements.getQuickLinkToLoadModule().clickMe();					
		}else {
			logger.info(moduleName + " module already loaded");
		}
	}
	
	public LeftNavBar setLeftNavBar(ContextManager contextManager) {
		logger.info("Creating left nav-bar for " + moduleName + " module");
		leftNavBar =  new LeftNavBar(driver, contextManager);		
		return leftNavBar;
	}
	
	public TopRightNavBar setNavBar(ContextManager contextManager) {
		logger.info("Creating top-right nav-bar for " + moduleName + " module");
		topRightNavBar = new TopRightNavBar(driver, contextManager);
		topRightNavBar.loadElements(moduleElements.getElementStrategy());
		return topRightNavBar;
	}
	
	public LeftMenu setLeftMenu(ContextManager contextManager) {
		logger.info("Creating left menu for " + moduleName + " module");
		leftMenu =  new LeftMenu(driver, contextManager);
		moduleElements.setLeftMenuElements(leftMenu);
		return leftMenu;
	}
	
	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}
	
	public LeftMenu getlLeftMenu() {
		return leftMenu;
	}
	
}
