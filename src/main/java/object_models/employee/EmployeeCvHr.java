/**
 * 
 */
package object_models.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.Label;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeCvHr extends JSPanelWithIFrame{	
	private static final String PANEL_TITLE = "Employee HR CV";
		
	public EmployeeCvHr(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	public Label getCompanyLabel() {
		return new Label(By.className(""), "Company");
	}

}
