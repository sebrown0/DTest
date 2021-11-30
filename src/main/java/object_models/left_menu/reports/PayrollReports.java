package object_models.left_menu.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollReports extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Reports";
	public static final String PANEL_TITLE = "Payroll Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public PayrollReports(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}