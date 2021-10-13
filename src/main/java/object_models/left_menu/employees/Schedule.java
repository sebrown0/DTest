package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Schedule extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Schedule";
	public static final String PANEL_TITLE = MENU_TITLE;	
	public static final String MENU_PARENT_NAME = "Employees";
	
	public Schedule(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}
