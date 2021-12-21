package object_models.module_payroll.left_menu.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class CalculationStatistics extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Calculation Statistics";
	public static final String PANEL_TITLE = "Calculation Statistics";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CalculationStatistics(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}