package object_models.left_menu.reports;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class AbsenceRelatedReports extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Absence Related Reports";
	public static final String PANEL_TITLE = "Absence Related Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public AbsenceRelatedReports(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}