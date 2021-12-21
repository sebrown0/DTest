package object_models.module_payroll.left_menu.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Payslips extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Payslips";
	public static final String PANEL_TITLE = "Payslips";
	public static final String MENU_PARENT_NAME = "Reports";

	public Payslips(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}