/**
 * 
 */
package object_models.modules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import object_models.navigation.left_side_menu.LeftMenuPayroll;
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
	
	public ModuleLoader(WebDriver driver, ModuleElements moduleElements) {		
		this.moduleElements = moduleElements;
		this.driver = driver;
		setModuleName();
		checkDriver();		
	}

	private void setModuleName() {
		moduleName = moduleElements.getModuleName();
	}
	
	private void checkDriver() {
		if (driver == null) {	logger.error("Null driver"); }
	}
		
	public void loadModule(TopRightNavBar topRightNavBar, LeftMenuPayroll leftMenu) {
		if(!ModuleChecker.getCurrentModule(driver).equalsIgnoreCase(moduleName)){			
			logger.info(moduleName + " module not loaded. Loading now");
			moduleElements.getQuickLinkToLoadModule().clickMe();
			createNavAndMenus(topRightNavBar, leftMenu);				
		}else {
			logger.info(moduleName + " module already loaded");
		}
	}
	
	private void createNavAndMenus(TopRightNavBar topRightNavBar, LeftMenuPayroll leftMenu) {
		logger.debug("Creating nav bar and menus for " + moduleName + " module");
		topRightNavBar.loadElements(moduleElements.getElementStrategy());
		leftMenu = new LeftMenuPayroll(driver);
	}
	
}
