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
* Created: 08/02/2022 16:45:03
*/

public class ContactNumbers extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="08/02/2022")
	public static final String PANEL_TITLE = "Employee Contact Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="08/02/2022")
	public static final String MENU_TITLE = "Contact Numbers";
	@SiteMap(author="SteveBrown", version="1.0.0", date="08/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="08/02/2022")
	public ContactNumbers(CoreData coreData){
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="08/02/2022")
	private void buildMyControls() {
		var myControls =
			List.of(
				new ControlData("employee_list", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']")))
			);
		super.buildPanelControls(myControls);
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="08/02/2022")
	@TestControl(type="button")
	public DynamicTest buttonEmployee_list () {
		return DynamicTest.dynamicTest("[buttonEmployee_list] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}

}