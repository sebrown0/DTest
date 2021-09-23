package object_models.reports;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class VisualReports extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Visual Reports";	

	public VisualReports(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
