package object_models.left_menu.employee_others;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Loans extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Loans";
	public static final String PANEL_TITLE = "Employee Loans";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Loans(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);		
	}
	
	// Elements

	// Tabs
}