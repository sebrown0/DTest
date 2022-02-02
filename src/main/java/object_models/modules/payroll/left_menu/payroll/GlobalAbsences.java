package object_models.modules.payroll.left_menu.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalAbsences extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Global Absences";
	public static final String PANEL_TITLE = "Global Absences Grid";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalAbsences(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}