/**
 * 
 */
package object_models.modules.common.nav.nav_bar_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.drop_down_forms.top_right_nav_bar.UserAvatar;
import object_models.helpers.Closable;
import object_models.modules.common.nav.NavBarElement;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 */
public class NavBarUserAvatar extends NavBarElement {
	public static final String ORIGINAL_NAME = "User Avatar";
	
	public NavBarUserAvatar(CoreData coreData) {
		super(coreData, ORIGINAL_NAME);
	}
			
	@Override
	public Closable clickElement() {
		WebElement el = driver.findElement(By.cssSelector("a[class='nav-link nav-pill user-avatar']"));
		el.click();
		return new UserAvatar(driver, ORIGINAL_NAME);
	}
}
