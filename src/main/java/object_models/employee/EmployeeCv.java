/**
 * 
 */
package object_models.employee;

import org.openqa.selenium.By;

import object_models.element.Label;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCv extends JsPanelWithIFrame {		
	public static final String PANEL_TITLE = "Employee Payroll CV";
	
	public EmployeeCv(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	public Label getCompanyLabel() {
		return new Label(By.className(""), "Company");
	}

}
