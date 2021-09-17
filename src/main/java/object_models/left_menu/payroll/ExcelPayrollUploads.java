package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class ExcelPayrollUploads extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Excel Payroll Uploads";
	public static final String PANEL_TITLE = "Excel Payroll Adjustment Uploads";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public ExcelPayrollUploads(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}