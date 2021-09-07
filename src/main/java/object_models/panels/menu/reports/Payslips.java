package object_models.panels.menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Payslips extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Payslips";
	public static final String PANEL_TITLE = "Payslips";

	public Payslips(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}