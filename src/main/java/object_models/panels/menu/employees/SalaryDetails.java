package object_models.panels.menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class SalaryDetails extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Salary Details";
	public static final String MENU_TITLE =  "Salary Details";

	public SalaryDetails(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}