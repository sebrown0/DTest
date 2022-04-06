package object_models.modules.payroll.top_right_nav.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.openqa.selenium.By;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;
import control_builder.control_getters.group.ControlGetterGroup;
import control_builder.control_getters.group.ControlGetterInputGroup;
import control_builder.control_getters.single.ControlGetterButton;
import dynamic_tests.annotations.TestControl;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;
import site_mapper.annotations.SiteMap;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 05/04/2022 16:18:04
*/

public class EmployeeCreation extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	public static final String PANEL_TITLE = "Employee Creation Wizard";
	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	public static final String MENU_TITLE = "Employee Creation";
	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	public EmployeeCreation(){}

	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	public EmployeeCreation(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	private void buildMyControls() {
		ControlGetter back =
			new ControlGetterButton("Back", coreData, By.cssSelector("a[href='#previous']"));
		ControlGetter next =
			new ControlGetterButton("Next", coreData, By.cssSelector("a[href='#next']"));
		ControlGetterGroup pageFooterBtns =
			new ControlGetterInputGroup("PageFooterBtns", coreData, By.cssSelector("ul[role='menu']"))
				.addControls(Arrays.asList(back, next));
		var myControls =
			List.of(
				new ControlData(pageFooterBtns)
			);
		super.buildPanelControls(myControls);
	}

	public EmployeeCreationWizard getEmployeeCreationWizard() {
		return new EmployeeCreationWizard(coreData);
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonBackFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonBackFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="05/04/2022")
	@TestControl(type="element", subtype="Button")
	public DynamicTest ButtonNextFunctionTest () {
		return DynamicTest.dynamicTest("[ButtonNextFunctionTest] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

}