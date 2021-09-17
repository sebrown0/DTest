package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Unions extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Unions";
	public static final String PANEL_TITLE = "Employee Unions Details";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public Unions(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
