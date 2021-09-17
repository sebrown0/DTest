package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class SettingsPayroll extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Settings";
	public static final String PANEL_TITLE = "Payroll Related Settings";

	public SettingsPayroll(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}