package object_models.panels.menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Reports";
	public static final String PANEL_TITLE = "Payroll Reports";

	public PayrollReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}