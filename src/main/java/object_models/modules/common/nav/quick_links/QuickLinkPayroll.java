	/**
 * 
 */
package object_models.modules.common.nav.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class QuickLinkPayroll extends QuickLink {
	private static final By LOCATOR = By.xpath(".//a/i[contains(@class, 'fa fa-money')]");	
	
	public QuickLinkPayroll(WebDriver driver) {
		super(driver, LOCATOR);
	}	
	
}
