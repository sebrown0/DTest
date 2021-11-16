package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class SalaryDetails extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Salary Details";
	public static final String MENU_TITLE =  "Salary Details";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public SalaryDetails(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}