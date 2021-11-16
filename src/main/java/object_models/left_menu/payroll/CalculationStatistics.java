package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class CalculationStatistics extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Calculation Statistics";
	public static final String PANEL_TITLE = "Calculation Statistics";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CalculationStatistics(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}