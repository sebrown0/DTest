package object_models.left_menu.absence_statistics;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class OtherAbsenceStatistics extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Other Absence Statistics";
	public static final String PANEL_TITLE = "Payroll Other Statistics";
	public static final String MENU_PARENT_NAME = "Absence Statistics";

	public OtherAbsenceStatistics(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}