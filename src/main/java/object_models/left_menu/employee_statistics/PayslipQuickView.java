package object_models.left_menu.employee_statistics;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayslipQuickView extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Payslip Quick View";
	public static final String PANEL_TITLE = "Payslip Quick View";
	public static final String MENU_PARENT_NAME = "Employee Statistics";
	
	public PayslipQuickView(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}