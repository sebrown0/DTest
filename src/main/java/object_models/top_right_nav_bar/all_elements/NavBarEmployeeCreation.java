/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.employee_creation.EmployeeCreationWizard;
import object_models.helpers.Closable;
import object_models.strategies.click.ClickUsingJavaScript;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarEmployeeCreation extends NavBarElement {
	public static final String ORIGINAL_NAME = "Employee Creation";
		
	public NavBarEmployeeCreation(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
	
	@Override
	public Closable clickElement() {
		WebElement empCreation = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-plus')]"));
		ClickUsingJavaScript.performClick(driver, empCreation);
		return new EmployeeCreationWizard(driver);
	}
}
