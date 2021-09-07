package object_models.panels.menu.employee_others;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class AdvancesAndPayments extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Advances & Payments";
	public static final String PANEL_TITLE = "Advances and Pre Payments";

	public AdvancesAndPayments(WebDriver driver) {
		super(driver, PANEL_TITLE);		
	}

	// Elements

	// Tabs
}