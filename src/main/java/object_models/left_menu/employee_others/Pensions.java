package object_models.left_menu.employee_others;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Pensions extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Pensions";
	public static final String PANEL_TITLE = "Employee Pensions";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Pensions(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}