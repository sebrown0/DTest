package object_models.left_menu.additional_hours;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class ApplyAdditionalHours extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Apply";
	public static final String PANEL_TITLE = "Duty Hours";
	public static final String MENU_PARENT_NAME = "Additional Hours";

	public ApplyAdditionalHours(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);		
	}

	// Elements

	// Tabs
}