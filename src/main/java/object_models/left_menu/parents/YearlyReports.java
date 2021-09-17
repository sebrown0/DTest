package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class YearlyReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Yearly Reports";
	public static final String PANEL_TITLE = "Yearly Payroll Reports";

	public YearlyReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}