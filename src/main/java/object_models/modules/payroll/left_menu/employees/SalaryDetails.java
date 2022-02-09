package object_models.modules.payroll.left_menu.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import org.openqa.selenium.By;
import control_builder.*;
import site_mapper.annotations.SiteMap;
import org.junit.jupiter.api.DynamicTest;
import dynamic_tests.annotations.TestControl;
import object_models.panels.JsPanelWithIFrame;
import object_models.pages.homepage.CoreData;

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

	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public SalaryDetails(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	private void buildMyControls() {
		var myControls =
			List.of(
				new ControlData("new", new ControlGetterButton(coreData, By.cssSelector("button[name='NEW1']"))),
				new ControlData("save", new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
				new ControlData("search", new ControlGetterButton(coreData, By.cssSelector("button[name='QBF1']"))),
				new ControlData("delete", new ControlGetterButton(coreData, By.cssSelector("button[name='DELETE1']"))),
				new ControlData("clear", new ControlGetterButton(coreData, By.cssSelector("button[name='CLEAR1']"))),
				new ControlData("print", new ControlGetterButton(coreData, By.cssSelector("button[name='PRINT1']"))),
				new ControlData("employee_list", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']"))),
				new ControlData("salary_history", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']"))),
				new ControlData("combos", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-window-maximize']"))),
				new ControlData("grid_view", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fw fa-table']"))),
				new ControlData("existing_records", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fw fa-table']"))),
				new ControlData("documents", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-file-o']"))),
				new ControlData("calendar", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-calendar fa-fw']")))
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