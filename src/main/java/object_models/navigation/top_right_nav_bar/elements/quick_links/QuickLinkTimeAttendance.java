	/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class QuickLinkTimeAttendance extends QuickLink {
	private static final By LOCATOR = By.xpath(".//a/i[contains(@class, 'fa fa-clock')]");	
	
	public QuickLinkTimeAttendance(WebDriver driver) {
		super(driver, LOCATOR);
	}	
	
}
