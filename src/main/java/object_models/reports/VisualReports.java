package object_models.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class VisualReports extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Visual Reports";	

	public VisualReports(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs
}
