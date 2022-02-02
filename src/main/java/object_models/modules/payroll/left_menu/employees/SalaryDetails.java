package object_models.modules.payroll.left_menu.employees;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class SalaryDetails extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Salary Details";
	public static final String MENU_TITLE =  "Salary Details";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public SalaryDetails(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}