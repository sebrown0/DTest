package object_models.left_menu.employee_statistics;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class Fs3QuickView extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "FS3 Quick View";
	public static final String PANEL_TITLE = "FS3";
	public static final String MENU_PARENT_NAME = "Employee Statistics";
	
	public Fs3QuickView(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}