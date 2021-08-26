/**
 * 
 */
package object_models.modules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.NavBarPayrollElements;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLink;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinkPersonnel;
import providers.ModuleNames;

/**
 * @author Steve Brown
 *
 */
public class PersonnelModuleLoader implements ModuleLoader {
	private QuickLink quickLinkPersonnel;
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	private static final String MY_NAME = ModuleNames.PERSONNEL_NAME;
	
	public PersonnelModuleLoader(WebDriver driver) {
		if (driver == null) {
			logger.error("Null driver");
		}else {
			this.driver = driver;
			setQuickLink();	
		}		
	}
	
	private void setQuickLink() {
		quickLinkPersonnel = new QuickLinkPersonnel(driver);					
	}
	
	@Override
	public void loadModule(TopRightNavBar topRightNavBar, LeftMenu leftMenu) {
		if(!ModuleChecker.getCurrentModule(driver).equalsIgnoreCase(MY_NAME)){			
			logger.info("Personnel module not loaded. Loading now");
			quickLinkPersonnel.clickMe();
			createNavAndMenus(topRightNavBar, leftMenu);				
		}else {
			logger.info("Personnel module already loaded");
		}
	}
	
	private void createNavAndMenus(TopRightNavBar topRightNavBar, LeftMenu leftMenu) {
		logger.debug("Creating nav bar and menus for personnel module");
		topRightNavBar.loadElements(new NavBarPayrollElements(driver));
		leftMenu = new LeftMenu(driver);
	}
	
	@Override
	public String getModuleName() {
		return MY_NAME;
	}
	
}
