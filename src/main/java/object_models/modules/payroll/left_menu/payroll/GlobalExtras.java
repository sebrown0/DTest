package object_models.modules.payroll.left_menu.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalExtras extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Global Extras";
	public static final String PANEL_TITLE = "Global Extras Grid";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalExtras(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}