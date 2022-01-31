/**
 * 
 */
package object_models.module_payroll.left_menu.employees;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import context_manager.states.StateIframe;
import control_builder.ControlData;
import control_builder.ControlGetterButton;
import control_builder.ControlGetterDkGridEmployeeDetails;
import control_builder.ControlGetterDropdownCombo;
import control_builder.ControlGetterEmployeeSelection;
import control_builder.ControlGetterTextOut;
import controls.ComboSelectFromList;
import controls.TextOut;
import dynamic_tests.annotations.TestControl;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GroupControlNames;
import object_models.element.TextInOut;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add save(...).
 * @since 1.0
 *
 * Employee details page.
 */
public class EmployeeDetails extends JsPanelWithIFrame {
	private EmpDetailsTabs myTabs;

	public static final String PANEL_TITLE = "Employee Details";
	public static final String MENU_TITLE = PANEL_TITLE;
	public static final String MENU_PARENT_NAME = "Employees";
	
	public EmployeeDetails(CoreData coreData) {
		super(coreData, PANEL_TITLE);
		
		this.myTabs = new EmpDetailsTabs();
		buildMyControls();
	}
	
	// USE FOR TESTING WHEN WE WANT A BLANK OBJECT 
	public EmployeeDetails() { super();	}

	private void buildMyControls() {		
		var myControls = 
				List.of(
						new ControlData(EmployeeControlNames.EMP_CODE, new ControlGetterTextOut(coreData, By.id("FORM_ID"))),
						new ControlData(EmployeeControlNames.EMP_NAME, new ControlGetterTextOut(coreData, By.xpath("/html/body/form/div[3]/div[3]/div[2]/input"))),
						new ControlData(GroupControlNames.SELECT_EMP, new ControlGetterEmployeeSelection(coreData, By.cssSelector("i[class='fa fa-list']"))),
						new ControlData(GroupControlNames.COMBOS, new ControlGetterDropdownCombo(coreData, By.cssSelector("i[class='fa fa-window-maximize']"))),
						new ControlData(GroupControlNames.GRID_VIEW, new ControlGetterDkGridEmployeeDetails(coreData, By.cssSelector("i[class='fa fw fa-table']"))),
						new ControlData(GroupControlNames.SAVE, new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
						new ControlData(GroupControlNames.SEARCH, new ControlGetterButton(coreData, By.cssSelector("button[name='QBF1']")))
				);
		super.buildPanelControls(myControls);				
	}
		
	@TestControl(type = "button")
	public void buttonSave() {
		System.out.println("BUTTON SAVE TEST"); // TODO - remove or log 	
	}
	
	/*
	 * NOT USING AT PRESENT
	 */
//	@UiTest
//	public void save() {
//		System.out.println("->SAVING....." ); // TODO - remove or log 	
//		Optional<Control> cntrl = panelControl.getControl(GroupControlNames.SAVE);
//		cntrl.ifPresent(c -> {
//			Button b = (Button) c;
//			b.click();
//		});
//	}
	
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
