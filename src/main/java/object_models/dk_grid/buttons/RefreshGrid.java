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
public class RefreshGrid extends GridButton {
	public RefreshGrid(ElementButton elmntBtn) {
		super(elmntBtn, GridButtonNames.BTN_REFRESH_GRID_STATE);

	}

}
