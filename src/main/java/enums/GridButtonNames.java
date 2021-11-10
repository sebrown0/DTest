/**
 * 
 */
package enums;

import controls.ControlName;

/**
 * @author SteveBrown
 *
 */
public enum GridButtonNames implements ControlName {
	BTN_ADD("Add Record"),	
	BTN_DELETE("Delete Record"),
	BTN_SAVE("Save Changes"),	
	BTN_DOWNLOAD_TO_EXCEL("Download to Excel"),
	BTN_DOWNLOAD_TO_CSV("Download to CSV"),
	BTN_UPLOAD("Upload"),
	BTN_SAVE_GRID_STATE("Save Grid State"),
	BTN_REFRESH_GRID_STATE("Refresh Grid State"),
	BTN_RESET_GRID_STATE("Reset Grid State"),
	BTN_EMPLOYEE_VIEW("Employee View"),
	BTN_CREATE_HITLIST("Create HitList");
	
	private final String key;
	
	private GridButtonNames(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return key;
	}
}
