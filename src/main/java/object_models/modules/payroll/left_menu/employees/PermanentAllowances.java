package object_models.modules.payroll.left_menu.employees;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class PermanentAllowances extends JsPanelWithIFrame {	
	public static final String MENU_TITLE = "Permanent Allowances";
	public static final String PANEL_TITLE = "Employee Permanent Allowances";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public PermanentAllowances(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
