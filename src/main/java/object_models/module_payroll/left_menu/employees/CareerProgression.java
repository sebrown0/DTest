package object_models.module_payroll.left_menu.employees;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class CareerProgression extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Career Details";
	public static final String MENU_TITLE = "Career Progression";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public CareerProgression(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}
	
	// Elements

	// Tabs
}
