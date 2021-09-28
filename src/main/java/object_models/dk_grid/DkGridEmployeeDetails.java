package object_models.dk_grid;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class DkGridEmployeeDetails extends JSPanelWithIFrame {	
	private DkGrid<FindRowByEmpCode> grid;
	
	public static final String PANEL_TITLE = "Employee Details";	

	public DkGridEmployeeDetails(WebDriver driver) {
		super(driver, PANEL_TITLE);
		
		grid = new DkGrid<>(driver, new FindRowByEmpCode());
		grid.loadToolBar();
		grid.loadContent();
	}
	
	// Grid
	public DkGrid<FindRowByEmpCode> getGrid() {
		return grid;
	}
	
//	public void readGrid() {
//		DkGridToolBarReader toolBarReader = new DkGridToolBarReader(driver);
//		toolBarReader.read();
//	}
	
	// Elements

	// Tabs
}
