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
* Created: 02/02/2022 14:22:52
*/

public class Banks extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/02/2022")
	public static final String PANEL_TITLE = "Employee Banks and Unions Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/02/2022")
	public static final String MENU_TITLE = "Banks";
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="02/02/2022")
	public Banks(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="02/02/2022")
	private void buildMyControls() {
		var myControls =
			List.of(
				new ControlData("save", new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']")))
			);
		super.buildPanelControls(myControls);
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="02/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonSave () {
		return DynamicTest.dynamicTest("[buttonSave]", () -> fail("*NOT IMPLEMENTED*"));
	}

}