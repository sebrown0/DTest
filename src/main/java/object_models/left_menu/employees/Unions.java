package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Unions extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Unions";
	public static final String PANEL_TITLE = "Employee Unions Details";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public Unions(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}
