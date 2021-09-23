/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.employee_creation.EmployeeCreationWizard;
import object_models.helpers.Closable;
import object_models.strategies.click.ClickUsingJavaScript;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarAuthorisations extends NavBarElement {
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[1]/a/i");
	public static final String ORIGINAL_NAME = "Authorisations";
	
	public NavBarAuthorisations(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
	
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}

	@Override
	public Closable clickElement() {
		ClickUsingJavaScript.performClick(driver, LOCATOR);
		return new EmployeeCreationWizard(driver);
	}

}
