/**
 * 
 */
package control_builder;

import org.openqa.selenium.WebDriver;

import controls.Control;
import object_models.dk_grid.DkGrid;
import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class ControlGetterGrid <T extends KeyStrategyRow> extends ControlGetter {
	private DkGrid<T> grid;
	
	public ControlGetterGrid(WebDriver driver, T keyStrategy) {
		super(driver);
	
		grid = new DkGrid<>(driver, keyStrategy);
	}

	@Override
	public Control getControl() {
		return grid;
	}

}
