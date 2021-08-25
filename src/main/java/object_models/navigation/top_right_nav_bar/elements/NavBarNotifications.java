/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.navigation.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarNotifications extends NavBarElement {
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[8]/a/i");
	public static final String ORIGINAL_NAME = "Notifications";
	
	public NavBarNotifications(WebDriver driver) {
		super(driver, LOCATOR);
	}
	
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}	
}
