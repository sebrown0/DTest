package object_models.left_menu.employee_others;

import java.util.List;

import org.openqa.selenium.By;

import control_builder.ControlData;
import control_builder.ControlGetterButton;
import enums.control_names.GroupControlNames;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class AbsenceEntitlements extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Absence Entitlements";
	public static final String PANEL_TITLE = "Employee Absence Entitlements";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public AbsenceEntitlements(CoreData coreData) {
		super(coreData, PANEL_TITLE);
		
		buildMyControls();
	}
	
	private void buildMyControls() {		
		var myControls = 
				List.of(
						new ControlData(GroupControlNames.CLEAR, new ControlGetterButton(coreData, By.cssSelector("button[name='CLEAR1']")))
				);
		super.buildPanelControls(myControls);				
	}

	// Elements

	// Tabs
}