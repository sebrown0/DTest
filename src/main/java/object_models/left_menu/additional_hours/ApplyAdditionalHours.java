package object_models.left_menu.additional_hours;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class ApplyAdditionalHours extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Apply";
	public static final String PANEL_TITLE = "Duty Hours";
	public static final String MENU_PARENT_NAME = "Additional Hours";

	public ApplyAdditionalHours(WebDriver driver) {
		super(driver, PANEL_TITLE);		
	}

	// Elements

	// Tabs
}