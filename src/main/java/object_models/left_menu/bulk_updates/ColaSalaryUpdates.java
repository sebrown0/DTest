package object_models.left_menu.bulk_updates;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class ColaSalaryUpdates extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "COLA Salary Updates";
	public static final String PANEL_TITLE = "COLA Updates Grid";
	public static final String MENU_PARENT_NAME = "Bulk Updates";

	public ColaSalaryUpdates(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}