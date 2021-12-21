package object_models.module_payroll.left_menu.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollDetails extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Details";
	public static final String PANEL_TITLE = "PAY_DETAILS";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public PayrollDetails(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}