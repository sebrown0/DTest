package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalAbsences extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Global Absences";
	public static final String PANEL_TITLE = "Global Absences Grid";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalAbsences(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}