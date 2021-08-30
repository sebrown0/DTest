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
public class NavBarMyCoLastViewed extends NavBarElement { 
	public static final String ORIGINAL_NAME = "My Company / Last Viewed";
	
	public NavBarMyCoLastViewed(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}	

	@Override
	public ChildElement clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-building')]"));
		JqueryToolTip.goToToolTip(driver, el);		
		return new NoElement(ORIGINAL_NAME); //TODO create company last viewed
	}
}
