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
public enum GlobalAdjustmentControlNames implements ControlName {
	VIEW_ADJUSTMENT_TYPE("view adjustment type"),
	ACCEPT_CRITERIA("accept criteria");

	private final String key;
	
	private GlobalAdjustmentControlNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}