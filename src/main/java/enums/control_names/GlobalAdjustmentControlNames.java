/**
 * 
 */
package enums.control_names;

import controls.ControlName;

/**
 * @author Steve Brown
 *
 */
public enum GlobalAdjustmentControlNames implements ControlName {
	VIEW_ADJUSTMENT_TYPE("view adjustment type");

	private final String key;
	
	private GlobalAdjustmentControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}