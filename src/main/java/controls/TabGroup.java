/**
 * 
 */
package controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;
import control_builder.control_getters.ControlGetterTab;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TabGroup implements Control, ControlGroup {
	private By findBy;
	private List<ControlData> tabs = new ArrayList<>();
	private CoreData coreData;
	private Control currentControl;
	
	public TabGroup(CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.findBy = findBy;		
	}	

	public TabGroup addTabs(List<ControlGetter> tabs) {		
		if(tabs != null) {
			tabs.forEach(v -> {
				add((ControlGetterTab) v);
			});
		}
		return this;
	}
	
	private TabGroup add(ControlGetterTab tab) {
		tabs.add(new ControlData(tab));
		return this;
	}

	@Override //Control
	public boolean isAvailable() {
		System.out.println("TabGroup.isAvailable ** NOT IMPLEMENTED **"); // TODO - remove or log 	
		return false;
	}
	@Override //ControlGroup
	public Optional<Control> getControlByTitle(String title) {
		currentControl = null;
		getElementByTitle(title).ifPresent(e -> {
			currentControl = e.getControl();
		});
		return Optional.ofNullable(currentControl);
	}
	
	private Optional<ControlGetter> getElementByTitle(String title) {
		return 
			tabs
				.stream()
				.filter(e -> e.getCntrlName().equals(title))
				.map(c -> c.getControlGetter())
				.findFirst();
	}
	
	public CoreData getCoreData() {
		return coreData;
	}
	public By getFindBy() {
		return findBy;
	}

}
