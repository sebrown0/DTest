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
public class ResetGrid extends GridButton {
	public ResetGrid(ElementButton elmntBtn) {
		super(elmntBtn, GridButtonNames.BTN_RESET_GRID_STATE);

	}

}
