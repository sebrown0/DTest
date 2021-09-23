/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.Closable;
import object_models.helpers.Jquery;
import object_models.helpers.NoElement;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarUserManagment extends NavBarElement {
	public static final String ORIGINAL_NAME = "User Management";
	
	public NavBarUserManagment(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
	
	@Override
	public Closable clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-user-circle-o')]"));
		Jquery.goToElement(driver, el);
		return new NoElement(ORIGINAL_NAME); //TODO create user management obj.
	}	
}