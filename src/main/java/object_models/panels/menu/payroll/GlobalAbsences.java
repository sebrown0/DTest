package object_models.panels.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalAbsences extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Global Absences";
	public static final String PANEL_TITLE = "Global Absences Grid";

	public GlobalAbsences(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}