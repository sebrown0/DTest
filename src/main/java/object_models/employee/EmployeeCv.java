/**
 * 
 */
package object_models.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.element.Label;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCv extends JSPanelWithIFrame {		
	public static final String PANEL_TITLE = "Employee Payroll CV";
	
	public EmployeeCv(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	public Label getCompanyLabel() {
		return new Label(By.className(""), "Company");
	}

}
