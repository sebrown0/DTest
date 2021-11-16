package object_models.left_menu.employee_others;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Pensions extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Pensions";
	public static final String PANEL_TITLE = "Employee Pensions";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Pensions(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}