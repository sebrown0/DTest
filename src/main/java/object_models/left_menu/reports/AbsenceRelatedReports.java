package object_models.left_menu.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class AbsenceRelatedReports extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Absence Related Reports";
	public static final String PANEL_TITLE = "Absence Related Reports";
	public static final String MENU_PARENT_NAME = "Reports";

	public AbsenceRelatedReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}