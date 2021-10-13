package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class CalculatePayroll extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Calculate Payroll";
	public static final String PANEL_TITLE = "Payroll Calculation";;
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CalculatePayroll(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}