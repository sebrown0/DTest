package object_models.panels.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalAdjustments extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Global Adjustments";
	public static final String PANEL_TITLE = "Global Payroll Adjustments";

	public GlobalAdjustments(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}