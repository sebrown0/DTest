/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.Label;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCv extends JSPanelWithIFrame {		
	public static final String PANEL_TITLE = "Employee Payroll CV";
	
	public EmployeeCv(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	public Label getCompanyLabel() {
		return new Label(By.className(""), "Company");
	}

}
