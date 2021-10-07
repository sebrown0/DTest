/**
 * 
 */
package enums.control_names;

import controls.ControlName;

/**
 * @author SteveBrown
 *
 */
public enum EmployeeControlNames implements ControlName {
	EMP_CODE("code"), 
	EMP_NAME("name"), 
	SELECT_EMP("select"), 
	COMBOS("combos"), 
	GRID_VIEW("grid"), 
	DOCUMENTS("docs");

	private final String key;
	
	private EmployeeControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}