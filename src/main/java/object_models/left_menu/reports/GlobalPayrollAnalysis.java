package object_models.left_menu.reports;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalPayrollAnalysis extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Global Payroll Analysis";
	public static final String PANEL_TITLE = "Global Payroll Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public GlobalPayrollAnalysis(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}