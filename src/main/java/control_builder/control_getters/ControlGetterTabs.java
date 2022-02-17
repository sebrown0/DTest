/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import controls.Control;
import controls.TabGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterTabs extends ControlGetterGroup {
	private List<ControlGetter> tabs;
	
	public ControlGetterTabs(String name, CoreData coreData) {
		super(name, coreData);				
	}

	@Override
	public ControlGetterGroup addControls(List<ControlGetter> tabs) {
		this.tabs = tabs;
		return this;
	}
	
	@Override
	public Control getControl() {
		return new TabGroup(coreData, findBy).addTabs(tabs);
	}

}
