/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import org.openqa.selenium.By;

import controls.Control;
import controls.ControlGroup;
import controls.TabGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public final class ControlGetterTabs extends ControlGetterGroup {
	private List<ControlGetter> tabs;
		
	public ControlGetterTabs(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);				
	}

	@Override
	public ControlGetterGroup addControls(List<ControlGetter> tabs) {
		this.tabs = tabs;
		return this;
	}
	
	@Override
	public Control getControl() {
		return 
			new ControlGroup(super.getName(), driver, findBy)
				.addElements(tabs, new TabGroup());
	}

}
