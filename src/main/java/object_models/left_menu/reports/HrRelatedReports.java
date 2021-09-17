package object_models.left_menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class HrRelatedReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "HR Related Reports";
	public static final String PANEL_TITLE = "HR Related Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public HrRelatedReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}