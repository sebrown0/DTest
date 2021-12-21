package object_models.module_payroll.left_menu.bulk_updates;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeCreation extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Employee Creation";
	public static final String PANEL_TITLE = "New Employee Bulk Uploads";
	public static final String MENU_PARENT_NAME = "Bulk Updates";

	public EmployeeCreation(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
