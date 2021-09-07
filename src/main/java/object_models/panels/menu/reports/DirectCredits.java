package object_models.panels.menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DirectCredits extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Direct Credits";
	public static final String PANEL_TITLE = "Payroll Reports";

	public DirectCredits(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}