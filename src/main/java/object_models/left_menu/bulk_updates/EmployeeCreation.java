package object_models.left_menu.bulk_updates;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeCreation extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Employee Creation";
	public static final String PANEL_TITLE = "New Employee Bulk Uploads";
	public static final String MENU_PARENT_NAME = "Bulk Updates";

	public EmployeeCreation(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}
