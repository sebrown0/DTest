package object_models.module_payroll.left_menu.employees;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class PreviousEmployement extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Previous Employment";
	public static final String PANEL_TITLE = "Employee Previous Employment Totals";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public PreviousEmployement(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}
	// Elements

	// Tabs
}