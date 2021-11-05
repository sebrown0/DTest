/**
 * 
 */
package enums.control_names;

import controls.ControlName;

/**
 * @author SteveBrown
 *
 */
public enum GroupControlNames implements ControlName {
	SELECT_EMP("select"), 
	COMBOS("combos"), 
	GRID_VIEW("grid"), 
	DOCUMENTS("docs");

	private final String key;
	
	private GroupControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}