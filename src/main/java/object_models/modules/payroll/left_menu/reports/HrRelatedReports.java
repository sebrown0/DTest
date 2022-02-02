package object_models.modules.payroll.left_menu.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class HrRelatedReports extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "HR Related Reports";
	public static final String PANEL_TITLE = "HR Related Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public HrRelatedReports(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}