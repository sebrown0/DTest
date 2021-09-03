/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.Label;
import object_models.helpers.ChildElement;
import object_models.helpers.PageTitle;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCv implements ChildElement{	
	private JSPanelWithIFrame panel;	
	private Label company = new Label(By.className(""), "Company");
	
	public static final String PANEL_TITLE = "Employee Payroll CV";
	
	public EmployeeCv(WebDriver driver, String panelTitle) {
		this.panel = new JSPanelWithIFrame(driver, PANEL_TITLE);
	}

	public Label getCompanyLabel() {
		return company;
	}
	
	public PageTitle getTitle() {
		return panel.getTitle();
	}
	
	public void switchToIFrame() {
		panel.switchToIFrame();
	}
	
	public void close() {
		panel.close();
	}

}
