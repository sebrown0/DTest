/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.Label;
import object_models.helpers.ChildElement;
import object_models.helpers.Title;
import object_models.strategies.title.TitleInInnerHTML;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCvHr implements ChildElement{	
	private JSPanelWithIFrame panel;
	private Title title;
	private Label company = new Label(By.className(""), "Company");
	
	private static final String PANEL_TITLE = "Employee HR CV";
		
	public EmployeeCvHr(WebDriver driver) {
		title = new Title(By.cssSelector("span.jsPanel-title"), PANEL_TITLE, new TitleInInnerHTML());
		panel = new JSPanelWithIFrame(driver, title);
	}

	public Label getCompanyLabel() {
		return company;
	}
	
	public Title getTitle() {
		return title;
	}
	
	public void switchToIFrame() {
		panel.switchToIFrame();
	}
	
	public void close() {
		panel.close();
	}

}
