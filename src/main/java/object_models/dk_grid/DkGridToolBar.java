/**
 * 
 */
package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import enums.GridButtonNames;
import object_models.dk_grid.buttons.GridButton;
import object_models.element.ElementInput;
import object_models.element.ElementPointInTime;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0 
 */
public class DkGridToolBar {	
//	private Map<String, ElementButton> buttons = new HashMap<>();
	private Map<String, GridButton> buttons = new HashMap<>();
	private Optional<ElementInput> overallFilter;
	private Optional<ElementPointInTime> pointInTime;
//	private static List<ButtonData> buttonData;
	
//	static {
//		buttonData = 
//				List.of(
//						new ButtonData(GridButtonNames.BTN_ADD, "fa fa-fw fa-plus"),
//						new ButtonData(GridButtonNames.BTN_DELETE, "fa fa-fw fa-remove"),
//						new ButtonData(GridButtonNames.BTN_SAVE, "fa fa-fw fa-save"),
//						new ButtonData(GridButtonNames.BTN_DOWNLOAD_TO_CSV, "fa fa-fw fa-download"),
//						new ButtonData(GridButtonNames.BTN_UPLOAD, "fa fa-fw fa-upload"),
//						new ButtonData(GridButtonNames.BTN_SAVE_GRID_STATE, "fa fa-fw fa-table"),
//						new ButtonData(GridButtonNames.BTN_REFRESH_GRID_STATE, "fa fa-fw fa-refresh"),
//						new ButtonData(GridButtonNames.BTN_RESET_GRID_STATE, "fa fa-fw fa-rotate-left")
//				);
//	}
			
	/*
	 * Buttons
	 */
	public void addButton(GridButton btn) {	
		buttons.putIfAbsent(btn.buttonName().getName(), btn);
	}	
//	public void addButton(ElementKey btn) {
////		buttons.putIfAbsent(btn.getKey(buttonData), (ElementButton) btn);	
//		buttons.putIfAbsent(btn.getKey(buttonData), (GridButton) btn);
//	}	
	public Optional<GridButton> getButton(GridButtonNames key){
		Optional<GridButton>  btn = Optional.ofNullable(buttons.get(key.getName()));
		return btn;
	}
	public Map<String, GridButton> getButtons() {
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
