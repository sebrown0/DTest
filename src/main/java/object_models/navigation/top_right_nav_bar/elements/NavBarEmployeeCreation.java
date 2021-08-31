/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.navigation.NavBarElement;
import object_models.panels.employee_creation.EmployeeCreationWizard;
import object_models.strategies.click.ClickUsingJavaScript;

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
	public ChildElement clickElement() {
		WebElement empCreation = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-plus')]"));
		ClickUsingJavaScript.performClick(driver, empCreation);
		return new EmployeeCreationWizard(driver);
	}
}
