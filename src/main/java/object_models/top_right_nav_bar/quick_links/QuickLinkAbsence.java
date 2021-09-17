	/**
 * 
 */
package object_models.top_right_nav_bar.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class QuickLinkAbsence extends QuickLink {
	private static final By LOCATOR = By.xpath(".//a/i[contains(@class, 'fa fa-glass')]");	
	
	public QuickLinkAbsence(WebDriver driver) {
		super(driver, LOCATOR);
	}	
	
}
