/**
 * 
 */
package object_models.dk_grid.buttons;

import enums.GridButtonNames;
import object_models.element.ElementButton;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class SaveGrid extends GridButton {
	public SaveGrid(ElementButton elmntBtn) {
		super(elmntBtn, GridButtonNames.BTN_SAVE_GRID_STATE);

	}

}
