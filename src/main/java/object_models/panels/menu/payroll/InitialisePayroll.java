package object_models.panels.menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.forms.FormModal;

/**
 * @author Steve Brown
 *
 */
public class InitialisePayroll extends FormModal {
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String PARENT_NAME = "Payroll";
	
	public InitialisePayroll(WebDriver driver) {
		super(driver, PANEL_TITLE);	
	}

	@Override
	public String getParentName() {
		return PARENT_NAME;
	}
		
	// Elements

	// Tabs
}