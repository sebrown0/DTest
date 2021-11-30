package object_models.left_menu.additional_hours;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class ApplyAdditionalHours extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Apply";
	public static final String PANEL_TITLE = "Duty Hours";
	public static final String MENU_PARENT_NAME = "Additional Hours";

	public ApplyAdditionalHours(CoreData coreData) {
		super(coreData, PANEL_TITLE);		
	}

	// Elements

	// Tabs
}