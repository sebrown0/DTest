package object_models.modules.payroll.left_menu.employees;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Banks extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Banks and Unions Details";
	public static final String MENU_TITLE =  "Banks";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public Banks(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}