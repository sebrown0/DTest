/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import object_models.drop_down_forms.top_right_nav_bar.UserAvatar;
import object_models.helpers.Closable;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarUserAvatar extends NavBarElement {
	public static final String ORIGINAL_NAME = "User Avatar";
	
	public NavBarUserAvatar(WebDriver driver, ContextManager contextManager) {
		super(driver, ORIGINAL_NAME, contextManager);
	}
			
	@Override
	public Closable clickElement() {
		WebElement el = driver.findElement(By.cssSelector("a[class='nav-link nav-pill user-avatar']"));
		el.click();
		return new UserAvatar(driver, ORIGINAL_NAME);
	}
}
