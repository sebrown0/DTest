/**
 * 
 */
package control_builder.control_getters;

import org.openqa.selenium.By;

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
	
	public ControlGetterGrid(String name, By locator, CoreData coreData, T keyStrategy) {
		super(name, coreData);
	
		grid = new DkGrid<>(driver, locator, keyStrategy, coreData.getContextManager());
	}

	@Override
	public Control getControl() {
		return grid;
	}
	
}
