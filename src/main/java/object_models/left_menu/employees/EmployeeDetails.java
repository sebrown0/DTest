/**
 * 
 */
package object_models.left_menu.employees;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.ControlBuilder;
import controls.ControlGetterEmployeeSelection;
import controls.ControlGetterTextOut;
import controls.PageControl;
import enums.control_names.EmployeeControlNames;
import object_models.dk_grid.DkGridEmployeeDetails;
import object_models.element.ComboSelect;
import object_models.element.TextInOut;
import object_models.element.TextOut;
import object_models.helpers.Reload;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 * Employee details page.
 */
public class EmployeeDetails extends JSPanelWithIFrame implements Reload {
	private EmpDetailsTabs myTabs;
	private PageControl empControl;
	private boolean isChildLoaded = false;
	
	public static final String PANEL_TITLE = "Employee Details";
	public static final String MENU_TITLE = PANEL_TITLE;
	public static final String MENU_PARENT_NAME = "Employees";
	
	public EmployeeDetails(WebDriver driver) {
		super(driver, PANEL_TITLE);
				
		this.myTabs = new EmpDetailsTabs();
		buildControl();
	}
	
	// Control
	private void buildControl() {
		ControlBuilder builder = new ControlBuilder();
		builder
			.addControl(EmployeeControlNames.EMP_CODE, new ControlGetterTextOut(driver, By.id("FORM_ID")))
			.addControl(EmployeeControlNames.EMP_NAME, new ControlGetterTextOut(driver, By.xpath("/html/body/form/div[3]/div[3]/div[2]/input")))
			.addControl(EmployeeControlNames.SELECT_EMP, new ControlGetterEmployeeSelection(driver, By.cssSelector("i[class='fa fa-list']"), this));
		
		empControl = new PageControl(builder);				
	}
		
	public PageControl getEmployeeControl() {
		return empControl;
	}
			
	public DkGridEmployeeDetails showEmpDetailsGrid() {
		WebElement e = driver.findElement(By.cssSelector("i[class='fa fw fa-table']"));
		e.click();		
		isChildLoaded = true;
		return new DkGridEmployeeDetails(driver);
	}
	
	@Override
	public void reloadDefault() {
		switchToMe();		
	}
	
	private void switchToMe() {
		driver.switchTo().defaultContent();
		super.switchToIFrame();		
		isChildLoaded = false;
	}
	
	private void switchBackToFormIfNecessary() {
		if(isChildLoaded == true) {
			switchToMe();
		}			
	}
		
	public EmpDetailsTabs tab() {
		return this.myTabs;
	}
		
	public class EmpDetailsTabs{
		public BasicDetails basicDetails() {			
			return new BasicDetails();
		}		
		public Settings settings() {			
			return new Settings();
		}		
		public Suspension suspension() {			
			return new Suspension();
		}
		public GovtBonus govtBonus() {			
			return new GovtBonus();
		}
				
		public class BasicDetails {
			private WebElement tab;
			
			public BasicDetails() {
				switchBackToFormIfNecessary();
				tab = driver.findElement(By.xpath("//a[@href='#tab1']"));
				tab.click();
			}

			// This tab's elements						
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
		
		public class Settings {
			private WebElement tab;
			
			public Settings() {
				switchBackToFormIfNecessary();
				tab = driver.findElement(By.xpath("//a[@href='#tab2']"));
				tab.click();
			}

			// This tab's elements
			public TextInOut partTimerHoursPerDay() {
				return new TextInOut(driver, By.id("ACTUAL_BASIC"));
			}						
		}		

		public class Suspension {
			private WebElement tab;
			
			public Suspension() {
				switchBackToFormIfNecessary();
				tab = driver.findElement(By.xpath("//a[@href='#tab3']"));
				tab.click();
			}

			// This tab's elements
						
		}
		
		public class GovtBonus {
			private WebElement tab;
			
			public GovtBonus() {
				switchBackToFormIfNecessary();
				tab = driver.findElement(By.xpath("//a[@href='#tab4']"));
				tab.click();
			}

			// This tab's elements
						
		}
	}

}

// Actions
//public EmployeeSelection showEmployeeSelection() {
//	WebElement e = driver.findElement(By.cssSelector("i[class='fa fa-list']"));
//	e.click();
//	isChildLoaded = true;
//	return new EmployeeSelection(driver);
//}