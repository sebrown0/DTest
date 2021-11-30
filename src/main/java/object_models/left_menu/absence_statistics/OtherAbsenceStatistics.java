package object_models.left_menu.absence_statistics;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class OtherAbsenceStatistics extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Other Absence Statistics";
	public static final String PANEL_TITLE = "Payroll Other Statistics";
	public static final String MENU_PARENT_NAME = "Absence Statistics";

	public OtherAbsenceStatistics(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}