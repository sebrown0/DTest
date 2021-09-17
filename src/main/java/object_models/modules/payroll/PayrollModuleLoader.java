/**
 * 
 */
package object_models.modules.payroll;

import org.openqa.selenium.WebDriver;

import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll_only.LeftMenuPayroll;
import object_models.modules.common.ModuleElements;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.payroll.NavBarPayrollElements;
import object_models.top_right_nav_bar.quick_links.QuickLink;
import object_models.top_right_nav_bar.quick_links.QuickLinkPayroll;
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
