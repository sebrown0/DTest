package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.forms.FormModal;

/**
 * @author Steve Brown
 *
 */
public final class CloseAndLockPayroll extends FormModal {
	public static final String MENU_TITLE = "Close & Lock Payroll";
	public static final String PANEL_TITLE = "Close & Lock Payroll";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CloseAndLockPayroll(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}
	
	// Elements

	// Tabs
}