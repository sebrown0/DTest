/**
 * 
 */
package object_models.reports;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class DakarIntelligence extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Reports";

	public DakarIntelligence(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Elements

	// Tabs

}
