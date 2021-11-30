package object_models.left_menu.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DetailedAdjustments extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Detailed Adjustments";
	public static final String PANEL_TITLE = "Payroll Detailed Adjustments";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public DetailedAdjustments(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
