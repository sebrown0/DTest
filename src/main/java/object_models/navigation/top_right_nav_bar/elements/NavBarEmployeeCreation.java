/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.navigation.NavBarElement;
import object_models.panels.EmployeeCreationWizard;

/**
 * @author Steve Brown
 *
 */
public class NavBarEmployeeCreation extends NavBarElement{
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[1]/a/i");
	public static final String ORIGINAL_NAME = "Employee Creation";
	
	public NavBarEmployeeCreation(WebDriver driver) {
		super(driver, LOCATOR);
	}

	public EmployeeCreationWizard loadEmployeeCreation() {
		super.clickElement();
		return new EmployeeCreationWizard(super.driver);
	}
	
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}	
}
