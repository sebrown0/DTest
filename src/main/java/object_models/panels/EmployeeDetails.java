/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ComboSelect;
import object_models.element.TextInOut;
import object_models.element.TextOut;
import object_models.helpers.ChildElement;
import object_models.helpers.PageTitle;

/**
 * @author Steve Brown
 *
 */
public class EmployeeDetails implements ChildElement{
	private WebDriver driver;	
	private JSPanelWithIFrame panel;
	private Tab myTabs;
	
	public static final String PANEL_TITLE = "Employee Details";
			
	public EmployeeDetails(WebDriver driver) {
		this.driver = driver;		
		this.panel = new JSPanelWithIFrame(driver, PANEL_TITLE);
		this.myTabs = new Tab();

		switchToIFrame();		
	}
	
	public PageTitle getTitle() {
		return panel.getTitle();
	}
	
	private void switchToIFrame() {
		panel.switchToIFrame();
	}
		
	public void closePanel() {
		panel.close();
	}
	
	// Tabs
	public Tab tab() {
		return this.myTabs;
	}
	
	// Basic Details
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
	
	// Settings
	public TextInOut partTimerHoursPerDay() {
		return new TextInOut(driver, By.id("ACTUAL_BASIC"));
	}
	
	public class Tab{	
		private WebElement tab;
				
		public Tab basicDetails() {
			tab = driver.findElement(By.xpath("//a[@href='#tab1']"));
			return this;
		}
		public Tab settings() {
			tab = driver.findElement(By.xpath("//a[@href='#tab2']"));
			return this;
		}
		public Tab suspension() {
			tab = driver.findElement(By.xpath("//a[@href='#tab3']"));
			return this;
		}
		public Tab govtBonus() {
			tab = driver.findElement(By.xpath("//a[@href='#tab4']"));
			return this;
		}
		
		public void click() {
			tab.click();			
		}
	}
}
