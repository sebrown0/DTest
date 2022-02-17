package object_models.modules.payroll.left_menu.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;
import control_builder.control_getters.ControlGetterButton;
import control_builder.control_getters.ControlGetterComboSelectOnly;
import control_builder.control_getters.ControlGetterGroup;
import control_builder.control_getters.ControlGetterInputGroup;
import control_builder.control_getters.ControlGetterTab;
import control_builder.control_getters.ControlGetterTabs;
import controls.InputGroup;
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
* Created: 09/02/2022 16:27:24
*/

public class SalaryDetails extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String PANEL_TITLE = "Employee Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String MENU_TITLE = "Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@FunctionalInterface
	interface Title {
		WebElement getElement(String title);
	}
	
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public SalaryDetails(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	/*
	 * IF WE HAVE CONTROLS (THE SalaryDetailsControls.java FILE IS THERE)
	 * 'BUILD THEM'.
	 */
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	private void buildMyControls() {
		InputGroup grp = new InputGroup(coreData, By.cssSelector("div[class='input-group']"));
		grp
			.addElement("employee_list", By.cssSelector("div[title='Search Employee']"))
			.addElement("salary_history", By.cssSelector("div[title='View Salary History']"))
			.addElement("combos", By.cssSelector("div[title='Combos']"))
			.addElement("grid_view", By.cssSelector("div[title='Grid View for this Employee']"))
			.addElement("existing_records", By.cssSelector("div[title='Grid View for existing records']"))
			.addElement("documents", By.cssSelector("div[title='No Documents Attached']"))
			.addElement("calendar", By.cssSelector("div[title='Combos']"));
			
		//TODO --------------------------.............................
		ControlGetter reason = 
			new ControlGetterComboSelectOnly(
				"Reason", coreData,  By.cssSelector("span[id='select2-REASON-container']"), null);
		
		ControlGetterGroup salaryDetails = 
				new ControlGetterTab("SalaryDetails", coreData, By.cssSelector("a[id='tab-tab1']"))
					.addControls(Arrays.asList(reason));
		
		ControlGetterGroup tabs = 
				new ControlGetterTabs("Tabs", coreData)
					.addControls(Arrays.asList(salaryDetails));
				
		var myControls =
			List.of(					
				new ControlData(new ControlGetterInputGroup("EmpLookup", coreData, grp)),
				new ControlData(tabs),
				new ControlData(new ControlGetterButton("calendar", coreData, By.cssSelector("i[class='fa fa-calendar fa-fw']"))),
				new ControlData(new ControlGetterButton("new", coreData, By.cssSelector("button[name='NEW1']"))),
				new ControlData(new ControlGetterButton("save", coreData, By.cssSelector("button[name='SAVE']"))),
				new ControlData(new ControlGetterButton("search", coreData, By.cssSelector("button[name='QBF1']"))),
				new ControlData(new ControlGetterButton("delete", coreData, By.cssSelector("button[name='DELETE1']"))),
				new ControlData(new ControlGetterButton("clear", coreData, By.cssSelector("button[name='CLEAR1']"))),
				new ControlData(new ControlGetterButton("print", coreData, By.cssSelector("button[name='PRINT1']")))				
			);
						
		super.buildPanelControls(myControls);
	}
	
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonNew () {
		return DynamicTest.dynamicTest("[buttonNew] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSave () {
		return DynamicTest.dynamicTest("[buttonSave] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSearch () {
		return DynamicTest.dynamicTest("[buttonSearch] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonDelete () {
		return DynamicTest.dynamicTest("[buttonDelete] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonClear () {
		return DynamicTest.dynamicTest("[buttonClear] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonPrint () {
		return DynamicTest.dynamicTest("[buttonPrint] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonEmployee_list () {
		return DynamicTest.dynamicTest("[buttonEmployee_list] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSalary_history () {
		return DynamicTest.dynamicTest("[buttonSalary_history] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonCombos () {
		return DynamicTest.dynamicTest("[buttonCombos] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonGrid_view () {
		return DynamicTest.dynamicTest("[buttonGrid_view] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonExisting_records () {
		return DynamicTest.dynamicTest("[buttonExisting_records] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonDocuments () {
		return DynamicTest.dynamicTest("[buttonDocuments] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonCalendar () {
		return DynamicTest.dynamicTest("[buttonCalendar] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

}