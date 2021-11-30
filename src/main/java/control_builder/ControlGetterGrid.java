/**
 * 
 */
package control_builder;

import controls.Control;
import object_models.dk_grid.DkGrid;
import object_models.dk_grid.KeyStrategyRow;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterGrid <T extends KeyStrategyRow> extends ControlGetter {
	private DkGrid<T> grid;
	
	public ControlGetterGrid(CoreData coreData, T keyStrategy) {
		super(coreData);
	
		grid = new DkGrid<>(super.driver, keyStrategy, coreData.getContextManager());
	}

	@Override
	public Control getControl() {
		return grid;
	}

}
