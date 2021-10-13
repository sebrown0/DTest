package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class MonthlyReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Monthly Reports";
	public static final String PANEL_TITLE = "Monthly Payroll Reports";

	public MonthlyReports(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}