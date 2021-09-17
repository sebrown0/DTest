package object_models.left_menu.additional_hours;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Authorisation extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Authorisation";
	public static final String PANEL_TITLE = "Extra Hours Authoriser";
	public static final String MENU_PARENT_NAME = "Additional Hours";

	public Authorisation(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}
	
	// Elements

	// Tabs
}