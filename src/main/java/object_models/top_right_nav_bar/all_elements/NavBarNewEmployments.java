/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.drop_down_forms.top_right_nav_bar.NewEmployments;
import object_models.helpers.Closable;
import object_models.pages.homepage.CoreData;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarNewEmployments extends NavBarElement {	
	public static final String ORIGINAL_NAME = "New Employments";
	
	public NavBarNewEmployments(CoreData coreData) {
		super(coreData, ORIGINAL_NAME);
	}
	
	@Override
	public Closable clickElement() {
		WebElement el = super.getNavBar().findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-user-plus')]"));
		el.click();
		return new NewEmployments(driver, ORIGINAL_NAME);
	}
}
