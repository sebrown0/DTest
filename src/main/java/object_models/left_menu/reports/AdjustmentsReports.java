package object_models.left_menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class AdjustmentsReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Adjustments Reports";
	public static final String PANEL_TITLE = "Payroll Adjustments Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public AdjustmentsReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}