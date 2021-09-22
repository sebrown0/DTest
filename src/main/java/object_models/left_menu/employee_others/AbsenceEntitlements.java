package object_models.left_menu.employee_others;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class AbsenceEntitlements extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Absence Entitlements";
	public static final String PANEL_TITLE = "Employee Absence Entitlements";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public AbsenceEntitlements(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}