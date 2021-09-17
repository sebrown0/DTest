package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class CalculationStatistics extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Calculation Statistics";
	public static final String PANEL_TITLE = "Calculation Statistics";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CalculationStatistics(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}