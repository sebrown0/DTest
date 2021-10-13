package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class CareerProgression extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Career Details";
	public static final String MENU_TITLE = "Career Progression";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public CareerProgression(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}
	
	// Elements

	// Tabs
}
