/**
 * 
 */
package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import object_models.element.ElementButton;
import object_models.element.ElementInput;
import object_models.element.ElementPointInTime;

/**
 * @author SteveBrown
 *
 */
public class DkGridToolBar {	
	private Map<String, ElementButton> buttons = new HashMap<>();
	private Optional<ElementInput> overallFilter;
	private Optional<ElementPointInTime> pointInTime;
	
	public static final String BTN_SAVE = "Save Changes";
	public static final String BTN_DOWNLOAD_TO_EXCEL = "Download to Excel";
	public static final String BTN_DOWNLOAD_TO_CSV = "Download to CSV";
	public static final String BTN_UPLOAD = "Upload";
	public static final String BTN_SAVE_GRID_STATE = "Save Grid State";
	public static final String BTN_REFRESH_GRID_STATE = "Refresh Grid State";
	public static final String BTN_RESET_GRID_STATE = "Reset Grid State";
	public static final String BTN_EMPLOYEE_VIEW = "Employee View";
	public static final String BTN_CREATE_HITLIST = "Create HitList";
	
	/*
	 * Buttons
	 */
	public void addButton(ElementButton btn) {
		buttons.putIfAbsent(btn.getElementKey(), btn);	
	}	
	public Optional<ElementButton> getButton(String key){
		Optional<ElementButton>  btn = Optional.ofNullable(buttons.get(key));
		return btn;
	}
	public Map<String, ElementButton> getButtons() {
		return buttons;
	}
	/*
	 * Overall Filter
	 */
	public void setOverallFilter(ElementInput overallFilter) {
		this.overallFilter = Optional.ofNullable(overallFilter);
	}
	public Optional<ElementInput> getOverallFilter() {
		return overallFilter;
	}
	/*
	* Point in time
	*/
	public void setPointInTime(ElementPointInTime pit) {
		this.pointInTime = Optional.ofNullable(pit);
	}
	public Optional<ElementPointInTime> getPointInTime() {
		return pointInTime;
	}
}
