/**
 * 
 */
package control_builder.control_getters.group;

import java.util.List;

import org.openqa.selenium.By;

import control_builder.control_getters.ControlGetter;
import controls.Control;
import controls.ControlGroup;
import controls.adders.TabAdder;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public final class ControlGetterTab extends ControlGetterGroup {
	private List<ControlGetter> cntrls;

	public ControlGetterTab(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);
			
	}

	@Override
	public ControlGetterGroup addControls(List<ControlGetter> cntrls) {
		this.cntrls = cntrls;
		return this;
	}
	
	@Override
	public Control getControl() {
		return 
			new ControlGroup(driver, findBy)
				.addElements(cntrls, new TabAdder());
	}
	
}
