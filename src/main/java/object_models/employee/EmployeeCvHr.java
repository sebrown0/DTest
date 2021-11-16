/**
 * 
 */
package object_models.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.element.Label;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeCvHr extends JsPanelWithIFrame{	
	private static final String PANEL_TITLE = "Employee HR CV";
		
	public EmployeeCvHr(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	public Label getCompanyLabel() {
		return new Label(By.className(""), "Company");
	}

}
