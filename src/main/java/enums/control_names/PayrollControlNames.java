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
public enum PayrollControlNames implements ControlName {
	PAY_GROUP("pay group"),
	PAY_PERIODS("pay periods"),
	INIT_PAYROLL("initialise payroll");

	private final String key;
	
	private PayrollControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}