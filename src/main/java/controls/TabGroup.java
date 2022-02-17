/**
 * 
 */
package controls;

import java.util.List;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TabGroup implements ControlAdder {
	
	@Override
	public void addElement(ControlGetter controlGetter, List<ControlData> controlData) {
		controlData.add(new ControlData(controlGetter));
	}
	
}

//public class TabGroup implements Control, ZZZ_ControlGroup {
//	private By findBy;
//	private List<ControlData> tabs = new ArrayList<>();
//	private CoreData coreData;
//	private ControlFinder controlFinder;
//	
//	public TabGroup(CoreData coreData, By findBy) {
//		this.coreData = coreData;
//		this.findBy = findBy;		
//	}	
//
//	public TabGroup addTabs(List<ControlGetter> tabs) {		
//		if(tabs != null) {
//			tabs.forEach(v -> {
//				add((ControlGetterTab) v);
//			});
//		}
//		return this;
//	}
//	
//	private TabGroup add(ControlGetterTab tab) {
//		tabs.add(new ControlData(tab));
//		return this;
//	}
//
//	@Override //Control
//	public boolean isAvailable() {
//		System.out.println("TabGroup.isAvailable ** NOT IMPLEMENTED **"); // TODO - remove or log 	
//		return false;
//	}
//	@Override //ControlGroup
//	public Optional<Control> getControlByTitle(String title) {
//		controlFinder = new ControlFinder(tabs);
//		return controlFinder.getControlByTitle(title);
//	}
//		
//	public CoreData getCoreData() {
//		return coreData;
//	}
//	public By getFindBy() {
//		return findBy;
//	}
//
//}
