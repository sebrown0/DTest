package object_models.modules.payroll.left_menu.employee_others;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class AdvancesAndPayments extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Advances & Payments";
	public static final String PANEL_TITLE = "Advances and Pre Payments";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public AdvancesAndPayments(CoreData coreData) {
		super(coreData, PANEL_TITLE);		
	}

	// Elements

	// Tabs
}