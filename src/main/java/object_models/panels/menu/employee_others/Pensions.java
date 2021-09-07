package object_models.panels.menu.employee_others;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Pensions extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Pensions";
	public static final String PANEL_TITLE = "Employee Pensions";

	public Pensions(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}