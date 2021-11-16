package object_models.left_menu.employee_others;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class TaxArrears extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Tax Arrears";
	public static final String PANEL_TITLE = "Employee Tax Arrears";
	public static final String MENU_PARENT_NAME = "Employee Others";
	
	public TaxArrears(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}