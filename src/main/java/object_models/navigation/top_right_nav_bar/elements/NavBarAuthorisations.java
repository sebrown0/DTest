/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.ChildElement;
import object_models.navigation.NavBarElement;
import object_models.panels.EmployeeCreationWizard;
import object_models.strategies.click.ClickUsingJavaScript;

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
	public ChildElement clickElement() {
		ClickUsingJavaScript.performClick(driver, LOCATOR);
		return new EmployeeCreationWizard(driver);
	}

}
