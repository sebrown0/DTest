package object_models.panels.menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class CareerProgression extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Career Details";
	public static final String MENU_TITLE = "Career Progression";

	public CareerProgression(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}
	
	// Elements

	// Tabs
}
