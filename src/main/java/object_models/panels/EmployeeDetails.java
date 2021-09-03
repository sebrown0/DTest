/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.ComboSelect;
import object_models.element.TextInOut;
import object_models.element.TextOut;
import object_models.helpers.ChildElement;
import object_models.helpers.Title;
import object_models.strategies.title.TitleInInnerHTML;

/**
 * @author SteveBrown
 *
 */
public class EmployeeDetails implements ChildElement{

	private WebDriver driver;	
	private JSPanelWithIFrame panel;
	private Title title;
	
	public static final String PANEL_TITLE = "Employee Details";
			
	public EmployeeDetails(WebDriver driver, String panelTitle) {
		this.driver = driver;
//		this.title = new Title(By.cssSelector("span.jsPanel-title"), panelTitle, new TitleInInnerHTML());
		this.title = new Title(By.cssSelector("span[class='jsPanel-title']"), panelTitle, new TitleInInnerHTML());
//		this.title = new Title(By.className("jsPanel-title"), panelTitle, new TitleInInnerHTML());
		
		this.panel = new JSPanelWithIFrame(driver, title);
		
		switchToIFrame();		
	}
	
	public Title getTitle() {
		return title;
	}
	
	private void switchToIFrame() {
		panel.switchToIFrame();
	}
		
	public void closePanel() {
		panel.close();
	}
	
	public TextInOut employeeCode() {
		return new TextInOut(driver, By.id("FORM_ID"));
	}
	public TextInOut iDCardNumber() {
		return new TextInOut(driver, By.id("IDENTITY_CARD_NO"));
	}	
	public ComboSelect title() {
		return new ComboSelect(driver, By.cssSelector("#tab1 > div:nth-child(5) > div:nth-child(2) > span > span.selection > span"));
		
	}	
	public TextInOut name() {
		return new TextInOut(driver, By.id("NAME"));
	}
	public TextInOut surname() {
		return new TextInOut(driver, By.id("SURNAME"));
	}
	public TextOut age() {
		return new TextOut(driver, By.id("AGE"));
	}
}
