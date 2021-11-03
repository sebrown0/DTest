/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import object_models.employee.EmployeeCv;
import object_models.helpers.Closable;
import object_models.strategies.click.ClickUsingJavaScript;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarEmployeeCVPayroll extends NavBarElement {
	public static final String ORIGINAL_NAME = "Employee CV";
	
	public NavBarEmployeeCVPayroll(WebDriver driver, ContextManager contextManager) {
		super(driver, ORIGINAL_NAME, contextManager);
	}
			
	@Override
	public Closable clickElement() {
		WebElement el = super.getNavBar().findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-user')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return new EmployeeCv(super.driver, super.contextManager);
	}
}
