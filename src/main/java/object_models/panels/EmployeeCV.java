/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.Label;
import object_models.helpers.ChildElement;
import object_models.helpers.Title;
import object_models.helpers.TitleInInnerHTML;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCV implements ChildElement{	
	private JSPanelWithIFrame panel;
	private Title title;
	private Label company = new Label(By.className(""), "Company");
		
	public EmployeeCV(WebDriver driver, String panelTitle) {

		title = new Title(By.cssSelector("span.jsPanel-title"), panelTitle, new TitleInInnerHTML());
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
