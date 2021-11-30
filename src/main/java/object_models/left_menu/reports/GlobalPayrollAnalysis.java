package object_models.left_menu.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalPayrollAnalysis extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Global Payroll Analysis";
	public static final String PANEL_TITLE = "Global Payroll Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public GlobalPayrollAnalysis(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}