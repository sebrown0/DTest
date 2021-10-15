package object_models.left_menu.parents;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class YearlyReports extends JSPanelWithIFrame {
	public static final String MENU_PARENT_NAME = "";
	public static final String MENU_TITLE = "Yearly Reports";
	public static final String PANEL_TITLE = "Yearly Payroll Reports";

	public YearlyReports(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}