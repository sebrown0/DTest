package object_models.panels.menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalPayrollAnalysis extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Global Payroll Analysis";
	public static final String PANEL_TITLE = "Global Payroll Reports";

	public GlobalPayrollAnalysis(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}