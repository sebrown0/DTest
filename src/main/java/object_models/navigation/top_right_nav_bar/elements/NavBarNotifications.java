/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.navigation.NavBarElement;
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class NavBarNotifications extends NavBarElement {
	public static final String ORIGINAL_NAME = "Notifications";
	
	public NavBarNotifications(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
	
	@Override
	public ChildElement clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-sticky-note')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return null; //TODO create notifications obj.
	}
}