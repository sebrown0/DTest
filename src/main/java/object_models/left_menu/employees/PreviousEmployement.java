package object_models.left_menu.employees;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class PreviousEmployement extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Previous Employment";
	public static final String PANEL_TITLE = "Employee Previous Employment Totals";
	public static final String MENU_PARENT_NAME = "Employees";
	
	public PreviousEmployement(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}