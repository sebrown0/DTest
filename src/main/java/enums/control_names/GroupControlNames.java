/**
 * 
 */
package enums.control_names;

import controls.ControlName;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Add SAVE
 * @since 1.0
 *
 */
public enum GroupControlNames implements ControlName {
	SELECT_EMP("select"), 
	COMBOS("combos"), 
	GRID_VIEW("grid"), 
	DOCUMENTS("docs"),
	SAVE("save");

	private final String key;
	
	private GroupControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}