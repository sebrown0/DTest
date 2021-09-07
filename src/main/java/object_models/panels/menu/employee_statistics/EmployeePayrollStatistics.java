package object_models.panels.menu.employee_statistics;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeePayrollStatistics extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Employee Payroll Statistics";
	public static final String PANEL_TITLE = "Payroll Other Statistics";

	public EmployeePayrollStatistics(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}