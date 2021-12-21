package object_models.module_payroll.left_menu.employee_others;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class TaxArrears extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Tax Arrears";
	public static final String PANEL_TITLE = "Employee Tax Arrears";
	public static final String MENU_PARENT_NAME = "Employee Others";
	
	public TaxArrears(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}