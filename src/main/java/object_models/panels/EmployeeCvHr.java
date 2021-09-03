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
public class EmployeeCvHr implements ChildElement{	
	private JSPanelWithIFrame panel;	
	private Label company = new Label(By.className(""), "Company");
	
	private static final String PANEL_TITLE = "Employee HR CV";
		
	public EmployeeCvHr(WebDriver driver) {
		this.panel = new JSPanelWithIFrame(driver, PANEL_TITLE);
	}

	public Label getCompanyLabel() {
		return company;
	}
		
	public void switchToIFrame() {
		panel.switchToIFrame();
	}	
	public void close() {
		panel.close();
	}
	public PageTitle getTitle() {
		return panel.getTitle();
	}

}
