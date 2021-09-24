/**
 * 
 */
package object_models.left_menu.employees;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.dk_grid.DkGridEmployeeDetails;
import object_models.element.ComboSelect;
import object_models.element.TextInOut;
import object_models.element.TextOut;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeDetails extends JSPanelWithIFrame {
	private EmpDetailsTabs myTabs;
	
	public static final String PANEL_TITLE = "Employee Details";
	public static final String MENU_TITLE = PANEL_TITLE;
	public static final String MENU_PARENT_NAME = "Employees";
	
	public EmployeeDetails(WebDriver driver) {
		super(driver, PANEL_TITLE);
		
		this.myTabs = new EmpDetailsTabs();
	}
				
	// Actions
	public DkGridEmployeeDetails showEmpDetailsGrid() {
		WebElement e = driver.findElement(By.cssSelector("i[class='fa fw fa-table']"));
		e.click();		
		return new DkGridEmployeeDetails(driver);
	}
	
	// Tabs
	public EmpDetailsTabs tab() {
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
	
	public class EmpDetailsTabs{	
		private WebElement tab;
				
		public EmpDetailsTabs basicDetails() {
			tab = driver.findElement(By.xpath("//a[@href='#tab1']"));
			return this;
		}
		public EmpDetailsTabs settings() {
			tab = driver.findElement(By.xpath("//a[@href='#tab2']"));
			return this;
		}
		public EmpDetailsTabs suspension() {
			tab = driver.findElement(By.xpath("//a[@href='#tab3']"));
			return this;
		}
		public EmpDetailsTabs govtBonus() {
			tab = driver.findElement(By.xpath("//a[@href='#tab4']"));
			return this;
		}
		
		public void click() {
			tab.click();			
		}
	}
}
