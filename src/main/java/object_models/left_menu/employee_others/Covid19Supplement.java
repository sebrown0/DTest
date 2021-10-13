package object_models.left_menu.employee_others;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Covid19Supplement extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Covid-19 Supplement";
	public static final String PANEL_TITLE = "Employee COVID Screen";
	public static final String MENU_PARENT_NAME = "Employee Others";

	public Covid19Supplement(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);		
	}
	
	// Elements

	// Tabs
}
