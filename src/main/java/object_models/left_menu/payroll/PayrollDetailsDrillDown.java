package object_models.left_menu.payroll;

import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollDetailsDrillDown extends JsPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Details DrillDown";
	public static final String PANEL_TITLE = "Payroll Statistics";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public PayrollDetailsDrillDown(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

	// Grid	
	public void readGrid() {
//		System.out.println("->read grid->" + super.getIFrameAsElement().getAttribute("index"));

//		DkGridToolBarReader toolBarReader = new DkGridToolBarReader(driver);
//		toolBarReader.read();
	}
	
	// Elements

	// Tabs
}
