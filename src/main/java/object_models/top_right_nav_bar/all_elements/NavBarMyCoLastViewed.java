/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.drop_down_forms.MyCompanyLastViewed;
import object_models.helpers.Closable;
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
	public Closable clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-building')]"));
		el.click();
//		Jquery.goToElement(driver, el);		NO NEED FOR Jquery. SIMPLE CLICK SUFFICES
		return new MyCompanyLastViewed(driver, ORIGINAL_NAME);
	}
}
