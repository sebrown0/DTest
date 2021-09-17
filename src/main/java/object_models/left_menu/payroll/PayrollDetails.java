package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollDetails extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Details";
	public static final String PANEL_TITLE = "PAY_DETAILS";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public PayrollDetails(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}