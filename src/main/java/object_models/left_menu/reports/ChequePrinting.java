package object_models.left_menu.reports;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class ChequePrinting extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Cheque Printing";
	public static final String PANEL_TITLE = "Cheque Printing";
	public static final String MENU_PARENT_NAME = "Reports";

	public ChequePrinting(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	// Elements

	// Tabs
}