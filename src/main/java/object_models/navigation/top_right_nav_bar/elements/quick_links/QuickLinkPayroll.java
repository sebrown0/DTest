/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public class QuickLinkPayroll extends QuickLink{
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[11]/div/div[2]/a[2]"); 
	
	public QuickLinkPayroll(WebDriver driver) {
		super(driver, LOCATOR);
	}
}
