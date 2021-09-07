package object_models.panels.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DetailedAdjustments extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Detailed Adjustments";
	public static final String PANEL_TITLE = "Payroll Detailed Adjustments";

	public DetailedAdjustments(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
