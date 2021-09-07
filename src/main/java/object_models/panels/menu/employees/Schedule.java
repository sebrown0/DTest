package object_models.panels.menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Schedule extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Schedule";
	public static final String PANEL_TITLE = MENU_TITLE;	

	public Schedule(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
