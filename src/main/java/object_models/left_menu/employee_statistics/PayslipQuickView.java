package object_models.left_menu.employee_statistics;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayslipQuickView extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Payslip Quick View";
	public static final String PANEL_TITLE = "Payslip Quick View";
	public static final String MENU_PARENT_NAME = "Employee Statistics";
	
	public PayslipQuickView(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}