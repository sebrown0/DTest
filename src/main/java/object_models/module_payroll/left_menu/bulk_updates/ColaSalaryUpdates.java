package object_models.module_payroll.left_menu.bulk_updates;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class ColaSalaryUpdates extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "COLA Salary Updates";
	public static final String PANEL_TITLE = "COLA Updates Grid";
	public static final String MENU_PARENT_NAME = "Bulk Updates";

	public ColaSalaryUpdates(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}