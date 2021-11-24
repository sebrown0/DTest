/**
 * 
 */
package control_builder;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import controls.Control;
import object_models.dk_grid.DkGrid;
import object_models.dk_grid.KeyStrategyRow;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterGrid <T extends KeyStrategyRow> extends ControlGetter {
	private DkGrid<T> grid;
	
	public ControlGetterGrid(WebDriver driver, T keyStrategy, ContextManager cm) {
		super(driver);
	
		grid = new DkGrid<>(driver, keyStrategy, cm);
	}

	@Override
	public Control getControl() {
		return grid;
	}

}
