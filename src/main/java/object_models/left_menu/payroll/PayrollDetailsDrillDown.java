package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class PayrollDetailsDrillDown extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Payroll Details DrillDown";
	public static final String PANEL_TITLE = "Payroll Statistics";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public PayrollDetailsDrillDown(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
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
