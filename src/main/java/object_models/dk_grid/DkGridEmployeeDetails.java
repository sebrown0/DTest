package object_models.dk_grid;

import org.apache.logging.log4j.LogManager;

import controls.Control;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class DkGridEmployeeDetails extends JsPanelWithIFrame implements Control {	
	private DkGrid<FindRowByEmpId> grid;
	
	public static final String PANEL_TITLE = "Employee Details";	

	public DkGridEmployeeDetails(CoreData coreData) {
		super(coreData, PANEL_TITLE);
		
		grid = new DkGrid<>(driver, new FindRowByEmpId(), super.manager);
	}
	
	// Grid
	public DkGrid<FindRowByEmpId> getGrid() {
		return grid.loadGridIfNecessary();
	}
	
	@Override // Control
	public boolean isAvailable() {
		LogManager.getLogger().error("NOT IMPLEMENTED");		
		return false;
	}
	
}
