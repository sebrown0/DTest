package object_models.modules.payroll.left_menu.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import control_builder.ControlData;
import control_builder.ControlGetterButton;
import dynamic_tests.annotations.TestControl;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;
import site_mapper.annotations.SiteMap;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 09/02/2022 13:14:27
*/

public class ContactNumbers extends JsPanelWithIFrame {
	private Tab myTabs = new Tab();
	
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String PANEL_TITLE = "Employee Contact Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String MENU_TITLE = "Contact Numbers";
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public ContactNumbers(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	private void buildMyControls() {
		var myControls =
			List.of(
				new ControlData("employee_list", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']"))),
				new ControlData("existing_records", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fw fa-table']"))),
				new ControlData("save", new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
				new ControlData("clear", new ControlGetterButton(coreData, By.cssSelector("button[name='CLEAR1']")))
			);
		super.buildPanelControls(myControls);
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonEmployee_list () {
		return DynamicTest.dynamicTest("[buttonEmployee_list] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonExisting_records () {
		return DynamicTest.dynamicTest("[buttonExisting_records] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSave () {
		return DynamicTest.dynamicTest("[buttonSave] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonClear () {
		return DynamicTest.dynamicTest("[buttonClear] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	// Tabs
	public Tab tab() {
		return this.myTabs;
	}
			
	public class Tab{	
		private WebElement tab;
				
		public Tab contacts() {
			tab = driver.findElement(By.xpath("//a[@href='#tab1']"));
			return this;
		}
		
		public Tab emailDetails() {
			tab = driver.findElement(By.xpath("//a[@href='#tab2']"));
			return this;
		}
				
		public void click() {
			tab.click();			
		}
	}
}