package object_models.left_menu.employee_others;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Pensions extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Pensions";
	public static final String PANEL_TITLE = "Employee Pensions";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Pensions(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}