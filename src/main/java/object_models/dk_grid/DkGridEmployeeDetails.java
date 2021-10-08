package object_models.dk_grid;

import org.openqa.selenium.WebDriver;

import controls.Control;
import object_models.helpers.Reload;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DkGridEmployeeDetails extends JSPanelWithIFrame implements Control {	
	private DkGrid<FindRowByEmpCode> grid;
	private Reload reloadEmpDetails;
	
	public static final String PANEL_TITLE = "Employee Details";	

	public DkGridEmployeeDetails(WebDriver driver, Reload reloadEmpDetails) {
		super(driver, PANEL_TITLE);
		
		this.reloadEmpDetails = reloadEmpDetails;
		grid = new DkGrid<>(driver, new FindRowByEmpCode());
		grid.loadToolBar();
		grid.loadContent();
	}
	
	// Grid
	public DkGrid<FindRowByEmpCode> getGrid() {
		return grid;
	}
	
	/*
	 * Add close method and reload emp details
	 */
}
