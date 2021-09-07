package object_models.panels.menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Banks extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Banks and Unions Details";
	public static final String MENU_TITLE =  "Banks";

	public Banks(WebDriver driver) {
		super(driver, PANEL_TITLE);		
	}

	// Elements

	// Tabs
}