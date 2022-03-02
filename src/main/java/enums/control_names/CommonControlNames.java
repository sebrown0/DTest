/**
 * 
 */
package enums.control_names;

import controls.interfaces.ControlName;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public enum CommonControlNames implements ControlName {	
	COMPANY("company"),
	DEPARTMENT("department"),
	DK_GRID("grid"),
	CLOSE("close");

	private final String key;
	
	private CommonControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}