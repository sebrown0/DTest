package object_models.left_menu.absence_statistics;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeAccruals extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Employee Accruals";
	public static final String PANEL_TITLE = "Leave Accruals";
	public static final String MENU_PARENT_NAME = "Absence Statistics";

	public EmployeeAccruals(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}