package object_models.panels.menu.employees;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class PermanentAllowances extends JSPanelWithIFrame {	
	public static final String MENU_TITLE = "Permanent Allowances";
	public static final String PANEL_TITLE = "Employee Permanent Allowances";
	
	public PermanentAllowances(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
