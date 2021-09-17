package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class MonthlyReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Monthly Reports";
	public static final String PANEL_TITLE = "Monthly Payroll Reports";

	public MonthlyReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}