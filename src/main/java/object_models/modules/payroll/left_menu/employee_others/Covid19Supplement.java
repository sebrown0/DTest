package object_models.modules.payroll.left_menu.employee_others;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Covid19Supplement extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Covid-19 Supplement";
	public static final String PANEL_TITLE = "Employee COVID Screen";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Covid19Supplement(CoreData coreData) {
		super(coreData, PANEL_TITLE);		
	}
	
	// Elements

	// Tabs
}
