package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollStatistics extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Statistics";
	public static final String PANEL_TITLE = "Payroll Other Statistics";

	public PayrollStatistics(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}