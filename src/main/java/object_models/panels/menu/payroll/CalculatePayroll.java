package object_models.panels.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class CalculatePayroll extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Calculate Payroll";
	public static final String PANEL_TITLE = "Payroll Calculation";;

	public CalculatePayroll(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}