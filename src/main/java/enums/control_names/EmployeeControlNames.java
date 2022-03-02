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
public enum EmployeeControlNames implements ControlName {
	EMP_CODE("code"), 
	EMP_NAME("name"),
	FULL_OR_PART_TIME("full or part time"),
	EMPLOYEES("employees"),	
	EMPLOYEE_VIEW("employee view");

	private final String key;
	
	private EmployeeControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}