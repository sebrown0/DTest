/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.helpers.JqueryToolTip;
import object_models.helpers.NoElement;
import object_models.navigation.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarUserAvatar extends NavBarElement {
	public static final String ORIGINAL_NAME = "User Avatar";
	
	public NavBarUserAvatar(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
			
	@Override
	public ChildElement clickElement() {
		WebElement el = driver.findElement(By.cssSelector("a[class='nav-link nav-pill user-avatar']"));
		JqueryToolTip.goToToolTip(driver, el);	
		return new NoElement(ORIGINAL_NAME); //TODO create user avatar object
	}
}
