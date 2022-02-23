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
import control_builder.control_getters.single.ControlGetterButton;
import control_builder.control_getters.group.ControlGetterInputGroup;
import control_builder.control_getters.group.ControlGetterRow;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 23/02/2022 11:53:53
*/

public class SalaryDetails extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="23/02/2022")
	public static final String PANEL_TITLE = "Employee Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="23/02/2022")
	public static final String MENU_TITLE = "Salary Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="23/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="23/02/2022")
	public SalaryDetails(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="23/02/2022")
	private void buildMyControls() {
		ControlGetter calendar =
			new ControlGetterButton("Calendar", coreData, By.cssSelector("i[class='fa fa-calendar fa-fw']"));
		ControlGetter newRec =
			new ControlGetterButton("NewRec", coreData, By.cssSelector("button[name='NEW1']"));
		ControlGetter save =
			new ControlGetterButton("Save", coreData, By.cssSelector("button[name='SAVE']"));
		ControlGetter search =
			new ControlGetterButton("Search", coreData, By.cssSelector("button[name='QBF1']"));
		ControlGetter delete =
			new ControlGetterButton("Delete", coreData, By.cssSelector("button[name='DELETE1']"));
		ControlGetter clear =
			new ControlGetterButton("Clear", coreData, By.cssSelector("button[name='CLEAR1']"));
		ControlGetter print =
			new ControlGetterButton("Print", coreData, By.cssSelector("button[name='PRINT1']"));
		ControlGetterGroup footerButtons =
			new ControlGetterRow("FooterButtons", coreData)
				.addControls(Arrays.asList(newRec, save, search, delete, clear, print));
		ControlGetterGroup empLookup =
			new ControlGetterInputGroup("EmpLookup", coreData, By.cssSelector("div[class='input-group date datepicker']"))
				.addControls(Arrays.asList(calendar));
		var myControls =
			List.of(
				new ControlData(empLookup),
				new ControlData(footerButtons)
			);
		super.buildPanelControls(myControls);
	}


}