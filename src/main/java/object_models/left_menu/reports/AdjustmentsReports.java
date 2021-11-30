package object_models.left_menu.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class AdjustmentsReports extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Adjustments Reports";
	public static final String PANEL_TITLE = "Payroll Adjustments Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public AdjustmentsReports(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}