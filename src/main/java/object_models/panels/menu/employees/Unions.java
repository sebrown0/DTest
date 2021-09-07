package object_models.panels.menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Unions extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Unions";
	public static final String PANEL_TITLE = "Employee Unions Details";

	public Unions(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
