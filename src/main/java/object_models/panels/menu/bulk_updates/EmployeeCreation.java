package object_models.panels.menu.bulk_updates;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeCreation extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Employee Creation";
	public static final String PANEL_TITLE = "New Employee Bulk Uploads";

	public EmployeeCreation(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
