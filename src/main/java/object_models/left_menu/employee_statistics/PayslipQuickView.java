package object_models.left_menu.employee_statistics;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayslipQuickView extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Payslip Quick View";
	public static final String PANEL_TITLE = "Payslip Quick View";
	public static final String MENU_PARENT_NAME = "Employee Statistics";
	
	public PayslipQuickView(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}