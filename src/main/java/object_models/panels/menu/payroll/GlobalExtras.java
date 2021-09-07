package object_models.panels.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalExtras extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Global Extras";
	public static final String PANEL_TITLE = "Global Extras Grid";

	public GlobalExtras(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}