package object_models.forms.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.forms.FormModal;

/**
 * @author Steve Brown
 *
 */
public class InitialisePayroll extends FormModal {
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	
	public InitialisePayroll(WebDriver driver) {
		super(driver, PANEL_TITLE);	
	}
		
	// Elements

	// Tabs
}