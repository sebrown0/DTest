/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.helpers.Closable;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class NavBarEmployeeCreation extends NavBarElement {
	public static final String ORIGINAL_NAME = "Employee Creation";
		
	public NavBarEmployeeCreation(WebDriver driver, ContextManager contextManager) {
		super(driver, ORIGINAL_NAME, contextManager);
	}
	
	@Override
	public Closable clickElement() {
		WebElement el = super.getNavBar().findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-plus')]"));
		el.click(); 	
		return new EmployeeCreationWizard(driver, super.contextManager);
	}
}
