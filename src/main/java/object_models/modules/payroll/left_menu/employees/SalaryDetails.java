package object_models.modules.payroll.left_menu.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import java.util.Arrays;
import org.openqa.selenium.By;
import control_builder.*;
import site_mapper.annotations.SiteMap;
import org.junit.jupiter.api.DynamicTest;
import dynamic_tests.annotations.TestControl;
import control_builder.control_getters.ControlGetter;
import control_builder.control_getters.group.ControlGetterGroup;
import object_models.panels.JsPanelWithIFrame;
import control_builder.control_data.ControlData;
import object_models.pages.homepage.CoreData;
import control_builder.control_getters.single.ControlGetterTextOut;
import control_builder.control_getters.single.ControlGetterButton;
import control_builder.control_getters.group.ControlGetterInputGroup;
import control_builder.control_getters.group.ControlGetterTabs;
import control_builder.control_getters.single.ControlGetterLabel;
import control_builder.control_getters.group.ControlGetterTab;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 17/03/2022 14:56:46
*/

public class SalaryDetails extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	public static final String PANEL_TITLE = "Employee Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	public static final String MENU_TITLE = "Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	public SalaryDetails(){}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	public SalaryDetails(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	private void buildMyControls() {
		ControlGetter formID =
			new ControlGetterTextOut("FormID", coreData, By.id("FORM_ID"));
		ControlGetter employeeList =
			new ControlGetterButton("EmployeeList", coreData, By.cssSelector("div[title='Search Employee']"));
		ControlGetter salaryHistory =
			new ControlGetterButton("SalaryHistory", coreData, By.cssSelector("div[title='View Salary History']"));
		ControlGetter combos =
			new ControlGetterButton("Combos", coreData, By.cssSelector("div[title='Combos']"));
		ControlGetter gridView =
			new ControlGetterButton("GridView", coreData, By.cssSelector("div[title='Grid View for this Employee']"));
		ControlGetter existingRecords =
			new ControlGetterButton("ExistingRecords", coreData, By.cssSelector("div[title='Grid View for existing records']"));
		ControlGetter documents =
			new ControlGetterButton("Documents", coreData, By.cssSelector("div[title='No Documents Attached']"));
		ControlGetter calendar =
			new ControlGetterButton("Calendar", coreData, By.cssSelector("i[class='fa fa-calendar fa-fw']"));
		ControlGetter labelReason =
			new ControlGetterLabel("LabelReason", coreData, By.cssSelector("label[for='REASON']"));
		ControlGetterGroup salaryDetails =
			new ControlGetterTab("SalaryDetails", coreData, By.cssSelector("a[id='tab-tab1']"))
				.addControls(Arrays.asList(labelReason));
		ControlGetterGroup tabs =
			new ControlGetterTabs("Tabs", coreData, By.cssSelector("ul[class='nav nav-tabs']"))
				.addControls(Arrays.asList(salaryDetails));
		ControlGetterGroup datePicker =
			new ControlGetterInputGroup("DatePicker", coreData, By.cssSelector("div[class='input-group date datepicker']"))
				.addControls(Arrays.asList(calendar));
		ControlGetterGroup empLookup =
			new ControlGetterInputGroup("EmpLookup", coreData, By.cssSelector("div[class='input-group']"))
				.addControls(Arrays.asList(formID, employeeList, salaryHistory, combos, gridView, existingRecords, documents));
		var myControls =
			List.of(
				new ControlData(empLookup),
				new ControlData(datePicker),
				new ControlData(tabs),
				new ControlData(salaryDetails)
			);
		super.buildPanelControls(myControls);
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="TextOut")
	public DynamicTest TextOutFormIDFunctionTest () {
		return DynamicTest.dynamicTest("[TextOutFormIDFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonEmployeeListFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonEmployeeListFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonSalaryHistoryFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonSalaryHistoryFunctionTest]", () -> fail("*NOT IMPLEMENTED*"));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonCombosFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonCombosFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonGridViewFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonGridViewFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonExistingRecordsFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonExistingRecordsFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonDocumentsFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonDocumentsFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonCalendarFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonCalendarFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="17/03/2022")
	@TestControl(type="container", subtype="none")
	public DynamicTest ContainerTabsFunctionTest () {
		return DynamicTest.dynamicTest("[ContainerTabsFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

}