package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalExtras extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Global Extras";
	public static final String PANEL_TITLE = "Global Extras Grid";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalExtras(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}