/**
 * 
 */
package object_models.modules.common.nav.nav_bar_elements;

import org.openqa.selenium.By;

import object_models.helpers.Closable;
import object_models.modules.common.nav.NavBarElement;
import object_models.modules.payroll.top_right_nav.employees.EmployeeCreation;
import object_models.pages.homepage.CoreData;
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class NavBarAuthorisations extends NavBarElement {
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[1]/a/i");
	public static final String ORIGINAL_NAME = "Authorisations";
	
	public NavBarAuthorisations(CoreData coreData) {
		super(coreData, ORIGINAL_NAME);
	}
	
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}

	@Override
	public Closable clickElement() {
		ClickUsingJavaScript.performClick(driver, LOCATOR);
		return new EmployeeCreation(super.coreData);
	}

}
