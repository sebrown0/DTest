package object_models.panels.menu.absence_statistics;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class OtherAbsenceStatistics extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Other Absence Statistics";
	public static final String PANEL_TITLE = "Payroll Other Statistics";

	public OtherAbsenceStatistics(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}