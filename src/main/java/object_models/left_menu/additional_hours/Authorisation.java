package object_models.left_menu.additional_hours;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Authorisation extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Authorisation";
	public static final String PANEL_TITLE = "Extra Hours Authoriser";
	public static final String MENU_PARENT_NAME = "Additional Hours";

	public Authorisation(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}
	
	// Elements

	// Tabs
}