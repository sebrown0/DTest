/**
 * 
 */
package object_models.modules;

import org.openqa.selenium.WebDriver;

import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.navigation.top_right_nav_bar.NavBarPayrollElements;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLink;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinkPayroll;
import providers.ModuleNames;

/**
 * @author Steve Brown
 *
 * The required elements of the payroll module.
 * 
 */
public class PayrollModuleLoader implements ModuleElements {
	private WebDriver driver;
	
	public PayrollModuleLoader(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public NavBarElementStrategy getElementStrategy() {
		return new NavBarPayrollElements(driver);
	}

	@Override
	public QuickLink getQuickLinkToLoadModule() {
		return new QuickLinkPayroll(driver);
	}

	@Override
	public String getModuleName() {
		return ModuleNames.PAYROLL_NAME;
	}

	@Override
	public void setLeftMenuElements(LeftMenu menu) {
		menu.setElements(new LeftMenuPayroll());
	}
		
}
