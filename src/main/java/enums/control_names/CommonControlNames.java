/**
 * 
 */
package enums.control_names;

import controls.ControlName;

/**
 * @author Steve Brown
 *
 */
public enum CommonControlNames implements ControlName {	
	COMPANY("company"),
	DEPARTMENT("department"),
	DK_GRID("grid");

	private final String key;
	
	private CommonControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}