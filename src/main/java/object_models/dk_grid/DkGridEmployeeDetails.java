package object_models.dk_grid;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import controls.Control;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DkGridEmployeeDetails extends JSPanelWithIFrame implements Control {	
	private DkGrid<FindRowByEmpId> grid;
	
	public static final String PANEL_TITLE = "Employee Details";	

	public DkGridEmployeeDetails(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
		
		grid = new DkGrid<>(driver, new FindRowByEmpId());
	}
	
	// Grid
	public DkGrid<FindRowByEmpId> getGrid() {
		return grid.loadGridIfNecessary();
	}
	
	/*
	 * Add close method and reload emp details
	 */
}
