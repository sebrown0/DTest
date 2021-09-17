/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.helpers.Jquery;
import object_models.helpers.NoElement;
import object_models.top_right_nav_bar.common.NavBarElement;

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
		Jquery.goToElement(driver, el);		
		return new NoElement(ORIGINAL_NAME); //TODO create company last viewed
	}
}
