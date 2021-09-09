package object_models.panels.menu.bulk_updates;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class ColaSalaryUpdates extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "COLA Salary Updates";
	public static final String PANEL_TITLE = "COLA Updates Grid";

	public ColaSalaryUpdates(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}