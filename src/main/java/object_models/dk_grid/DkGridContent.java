/**
 * 
 */
package object_models.dk_grid;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class DkGridContent <T extends KeyStrategyRow> {	
	private GridData<T> gridData;
	
	public DkGridContent() {
		gridData = new GridData<>();
	}

	public GridData<T> getGridData() {
		return gridData;
	}
	
}
