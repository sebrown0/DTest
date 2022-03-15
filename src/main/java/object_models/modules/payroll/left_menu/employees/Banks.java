package object_models.modules.payroll.left_menu.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;

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
* Created: 09/02/2022 15:57:51
*/

public class Banks extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String PANEL_TITLE = "Employee Banks and Unions Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String MENU_TITLE = "Banks";
	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
	public Banks(CoreData coreData){
		super(coreData, PANEL_TITLE);
//		buildMyControls();
	}

//	@SiteMap(author="SteveBrown", version="1.0.0", date="09/02/2022")
//	private void buildMyControls() {
//		var myControls =
//			List.of(
//				new ControlData("save", new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
//				new ControlData("clear", new ControlGetterButton(coreData, By.cssSelector("button[name='CLEAR1']"))),
//				new ControlData("employee_list", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']"))),
//				new ControlData("existing_records", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fw fa-table']")))
//			);
//		super.buildPanelControls(myControls);
//	}
	

}