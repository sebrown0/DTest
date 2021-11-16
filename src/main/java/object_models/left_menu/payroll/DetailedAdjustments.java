package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DetailedAdjustments extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Detailed Adjustments";
	public static final String PANEL_TITLE = "Payroll Detailed Adjustments";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public DetailedAdjustments(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}
