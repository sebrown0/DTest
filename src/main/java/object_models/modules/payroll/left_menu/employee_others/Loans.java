package object_models.modules.payroll.left_menu.employee_others;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Loans extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Loans";
	public static final String PANEL_TITLE = "Employee Loans";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Loans(CoreData coreData) {
		super(coreData, PANEL_TITLE);		
	}
	
	// Elements

	// Tabs
}