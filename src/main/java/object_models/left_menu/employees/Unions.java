package object_models.left_menu.employees;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Unions extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Unions";
	public static final String PANEL_TITLE = "Employee Unions Details";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public Unions(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
