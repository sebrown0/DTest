/**
 * 
 */
package enums.control_names;

import controls.ControlName;

/**
 * @author Steve Brown
 *
 */
public enum PayrollControlNames implements ControlName {
	PAY_GROUP("pay group"),
	PAY_PERIODS("pay periods");

	private final String key;
	
	private PayrollControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}