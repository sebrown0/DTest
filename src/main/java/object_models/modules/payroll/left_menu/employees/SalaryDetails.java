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
import control_builder.control_getters.single.ControlGetterComboSelectOnly;
import control_builder.control_getters.group.ControlGetterTab;
import control_builder.control_getters.single.ControlGetterTextSelect;
import control_builder.control_getters.group.ControlGetterRow;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 02/03/2022 14:06:04
*/

public class SalaryDetails extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/03/2022")
	public static final String PANEL_TITLE = "Employee Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/03/2022")
	public static final String MENU_TITLE = "Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/03/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="02/03/2022")
	public SalaryDetails(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="02/03/2022")
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
		ControlGetter labelGrade =
			new ControlGetterLabel("LabelGrade", coreData, By.cssSelector("label[for='GRADE_CODE']"));
		ControlGetter labelStepLevel =
			new ControlGetterLabel("LabelStepLevel", coreData, By.cssSelector("label[for='STEP_LEVEL']"));
		ControlGetter labelAnnualSalary =
			new ControlGetterLabel("LabelAnnualSalary", coreData, By.cssSelector("label[for='ANNUAL_SALARY_FIXED']"));
		ControlGetter labelNICategory =
			new ControlGetterLabel("LabelNICategory", coreData, By.cssSelector("label[for='NI_CATEGORY']"));
		ControlGetter labelIncrement =
			new ControlGetterLabel("LabelIncrement", coreData, By.cssSelector("label[for='INCREMENT_CODE']"));
		ControlGetter labelAnnualSalaryReduced =
			new ControlGetterLabel("LabelAnnualSalaryReduced", coreData, By.cssSelector("label[for='ANNUAL_SALARY_REDUCED']"));
		ControlGetter labelGradeDescription =
			new ControlGetterLabel("LabelGradeDescription", coreData, By.cssSelector("label[for='GRADE_DESC']"));
		ControlGetter labelYear =
			new ControlGetterLabel("LabelYear", coreData, By.cssSelector("label[for='INCREMENT_YEAR']"));
		ControlGetter reason =
			new ControlGetterComboSelectOnly("Reason", coreData, By.cssSelector("span[id='select2-REASON-container']"), By.cssSelector("span[id='select2-REASON-results']"));
		ControlGetter nICategory =
			new ControlGetterComboSelectOnly("NICategory", coreData, By.cssSelector("span[id='select2-NI_CATEGORY-container'"), By.cssSelector("span[id='select2-NI_CATEGORY-results']"));
		ControlGetter annualSalaryReduced =
			new ControlGetterTextOut("AnnualSalaryReduced", coreData, By.cssSelector("input[id='ANNUAL_SALARY_REDUCED']"));
		ControlGetter gradeDesc =
			new ControlGetterTextOut("GradeDesc", coreData, By.cssSelector("input[id='GRADE_DESC']"));
		ControlGetter year =
			new ControlGetterComboSelectOnly("Year", coreData, By.cssSelector("span[id='select2-INCREMENT_YEAR-container'"), By.cssSelector("span[id='select2-INCREMENT_YEAR-results']"));
		ControlGetter grade =
			new ControlGetterTextSelect("Grade", coreData, By.cssSelector("input[id='GRADE_CODE']"));
		ControlGetter search =
			new ControlGetterButton("Search", coreData, By.cssSelector("button[name='QBF1']"));
		ControlGetter clear =
			new ControlGetterButton("Clear", coreData, By.cssSelector("button[name='CLEAR1']"));
		ControlGetter newRec =
			new ControlGetterButton("NewRec", coreData, By.cssSelector("button[name='NEW1']"));
		ControlGetter save =
			new ControlGetterButton("Save", coreData, By.cssSelector("button[name='SAVE']"));
		ControlGetter delete =
			new ControlGetterButton("Delete", coreData, By.cssSelector("button[name='DELETE1']"));
		ControlGetter print =
			new ControlGetterButton("Print", coreData, By.cssSelector("button[name='PRINT1']"));
		ControlGetterGroup footerButtons =
			new ControlGetterRow("FooterButtons", coreData)
				.addControls(Arrays.asList(search, clear, newRec, save, delete, print));
		ControlGetterGroup remarks =
			new ControlGetterTab("Remarks", coreData, By.cssSelector("a[id='tab-tab2']"))
				.addControls(Arrays.asList());
		ControlGetterGroup gradeInput =
			new ControlGetterInputGroup("GradeInput", coreData, By.xpath("//i[@class='fa fa-list']/ancestor::div[@class='input-group']"))
				.addControls(Arrays.asList(grade));
		ControlGetterGroup salaryDetails =
			new ControlGetterTab("SalaryDetails", coreData, By.cssSelector("a[id='tab-tab1']"))
				.addControls(Arrays.asList(gradeInput, labelReason, labelGrade, labelStepLevel, labelAnnualSalary, labelNICategory, labelIncrement, labelAnnualSalaryReduced, labelGradeDescription, labelYear, reason, nICategory, annualSalaryReduced, gradeDesc, year));
		ControlGetterGroup tabs =
			new ControlGetterTabs("Tabs", coreData, By.cssSelector("ul[class='nav nav-tabs']"))
				.addControls(Arrays.asList(salaryDetails, remarks));
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
				new ControlData(footerButtons)
			);
		super.buildPanelControls(myControls);
	}


}