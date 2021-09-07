package object_models.panels.menu.employee_others;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class TaxArrears extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Tax Arrears";
	public static final String PANEL_TITLE = "Employee Tax Arrears";

	public TaxArrears(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}