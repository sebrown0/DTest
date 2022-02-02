package object_models.modules.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class SettingsPayroll extends JsPanelWithIFrame {
	public static final String MENU_PARENT_NAME = "";
	public static final String MENU_TITLE = "Settings";
	public static final String PANEL_TITLE = "Payroll Related Settings";

	public SettingsPayroll(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}