package object_models.modules.payroll.left_menu.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import context_manager.states.StateIframe;
import control_builder.ControlData;
import control_builder.ControlGetterButton;
import controls.ComboSelectFromList;
import controls.TextOut;
import dynamic_tests.annotations.TestControl;
import object_models.element.TextInOut;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;
import site_mapper.annotations.SiteMap;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 07/02/2022 10:07:48
*/

public class EmployeeDetails extends JsPanelWithIFrame {
	private EmpDetailsTabs myTabs;
	
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	public static final String PANEL_TITLE = "Employee Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	public static final String MENU_TITLE = "Employee Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	public EmployeeDetails(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	private void buildMyControls() {
		var myControls =
			List.of(
				new ControlData("save", new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
				new ControlData("search", new ControlGetterButton(coreData, By.cssSelector("button[name='QBF1']"))),
				new ControlData("clear", new ControlGetterButton(coreData, By.cssSelector("button[name='CLEAR1']"))),
				new ControlData("print", new ControlGetterButton(coreData, By.cssSelector("button[name='PRINT1']"))),
				new ControlData("employee_list", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']")))
			);
		super.buildPanelControls(myControls);
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSave () {
		return DynamicTest.dynamicTest("[buttonSave] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSearch () {
		return DynamicTest.dynamicTest("[buttonSearch] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonClear () {
		return DynamicTest.dynamicTest("[buttonClear] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonPrint () {
		return DynamicTest.dynamicTest("[buttonPrint] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonEmployee_list () {
		return DynamicTest.dynamicTest("[buttonEmployee_list] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	public EmpDetailsTabs tab() {
		manager.switchToStateInCurrentContext(StateIframe.class);
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
				tab = driver.findElement(By.id("tab-tab1"));
				tab.click();
			}

			// This tab's elements						
			public TextInOut iDCardNumber() {
				return new TextInOut(driver, By.id("IDENTITY_CARD_NO"));
			}	
			public ComboSelectFromList title() {
				return new ComboSelectFromList(coreData, By.cssSelector("#tab1 > div:nth-child(5) > div:nth-child(2) > span > span.selection > span"), By.id("select2-TITLE-results"));		
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
				tab = driver.findElement(By.xpath("//a[@href='#tab3']"));
				tab.click();
			}

			// This tab's elements
						
		}
		
		public class GovtBonus {
			private WebElement tab;
			
			public GovtBonus() {
				tab = driver.findElement(By.xpath("//a[@href='#tab4']"));
				tab.click();
			}

			// This tab's elements
						
		}
	}
}