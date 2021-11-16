package object_models.left_menu.absence_statistics;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class OtherAbsenceStatistics extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Other Absence Statistics";
	public static final String PANEL_TITLE = "Payroll Other Statistics";
	public static final String MENU_PARENT_NAME = "Absence Statistics";

	public OtherAbsenceStatistics(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}