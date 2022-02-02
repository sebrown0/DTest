package object_models.modules.payroll.left_menu.employee_statistics;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Fs3QuickView extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "FS3 Quick View";
	public static final String PANEL_TITLE = "FS3";
	public static final String MENU_PARENT_NAME = "Employee Statistics";
	
	public Fs3QuickView(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}