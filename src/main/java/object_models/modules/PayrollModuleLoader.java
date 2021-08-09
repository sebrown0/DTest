/**
 * 
 */
package object_models.modules;

import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.navigation.top_right_nav_bar.NavBarPayrollElements;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLink;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinkPayroll;

/**
 * @author SteveBrown
 *
 */
public class PayrollModuleLoader implements ModuleLoader {
	private QuickLink quickLinkPayroll;
	private WebDriver driver;
	
	public PayrollModuleLoader(WebDriver driver) throws NullDriverException {
		if (driver == null) {
			throw new NullDriverException(null);
		}else {
			this.driver = driver;
			setQuickLink();	
		}		
	}
	
	private void setQuickLink() {
		quickLinkPayroll = new QuickLinkPayroll(driver);					
	}
	
	@Override
	public void loadModule() {		
		quickLinkPayroll.clickMe();									
	}

	@Override
	public NavBarElementStrategy getElementStrategy() {
		return new NavBarPayrollElements(driver);
	}
	
}
