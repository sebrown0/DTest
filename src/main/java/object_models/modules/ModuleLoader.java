/**
 * 
 */
package object_models.modules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;

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
	
	public TopRightNavBar setNavBar() {
		logger.info("Creating nav bar for " + moduleName + " module");
		topRightNavBar = new TopRightNavBar(driver);
		topRightNavBar.loadElements(moduleElements.getElementStrategy());
		return topRightNavBar;
	}
	
	public LeftMenu setLeftMenu() {
		logger.info("Creating left menu for " + moduleName + " module");
		leftMenu =  new LeftMenu(driver);
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
