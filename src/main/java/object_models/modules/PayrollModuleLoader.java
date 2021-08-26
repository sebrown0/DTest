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
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinkPayroll;
import providers.ModuleNames;

/**
 * @author Steve Brown
 *
 */
public class PayrollModuleLoader implements ModuleLoader {
	private QuickLink quickLinkPayroll;
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	
	public PayrollModuleLoader(WebDriver driver) {
		if (driver == null) {
			logger.error("Null driver");
		}else {
			this.driver = driver;
			setQuickLink();	
		}		
	}
	
	private void setQuickLink() {
		quickLinkPayroll = new QuickLinkPayroll(driver);					
	}
	
	@Override
	public void loadModule(TopRightNavBar topRightNavBar, LeftMenu leftMenu) {
		if(!ModuleChecker.getCurrentModule(driver).equalsIgnoreCase(ModuleNames.PAYROLL_NAME)){			
			logger.info("Payroll module not loaded. Loading now");
			quickLinkPayroll.clickMe();
			createNavAndMenus(topRightNavBar, leftMenu);				
		}else {
			logger.info("Payroll module already loaded");
		}
	}
	
	private void createNavAndMenus(TopRightNavBar topRightNavBar, LeftMenu leftMenu) {
		logger.debug("Creating nav bar and menus for payroll module");
		topRightNavBar.loadElements(new NavBarPayrollElements(driver));
		leftMenu = new LeftMenu(driver);
	}
	
	@Override
	public String getModuleName() {
		return ModuleNames.PAYROLL_NAME;
	}
	
}
