package object_models.panels.menu.employee_others;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Covid19Supplement extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Covid-19 Supplement";
	public static final String PANEL_TITLE = "Employee COVID Screen";

	public Covid19Supplement(WebDriver driver) {
		super(driver, PANEL_TITLE);		
	}
	
	// Elements

	// Tabs
}
