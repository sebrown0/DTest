package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class MonthlyReports extends JsPanelWithIFrame {
	public static final String MENU_PARENT_NAME = "";
	public static final String MENU_TITLE = "Monthly Reports";
	public static final String PANEL_TITLE = "Monthly Payroll Reports";

	public MonthlyReports(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}