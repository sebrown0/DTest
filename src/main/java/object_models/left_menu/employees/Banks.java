package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Banks extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Banks and Unions Details";
	public static final String MENU_TITLE =  "Banks";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public Banks(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);		
	}

	// Elements

	// Tabs
}