/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import org.openqa.selenium.By;

import controls.Control;
import controls.ControlGroup;
import controls.adders.InputGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterInputGroup extends ControlGetterGroup {
	private List<ControlGetter> cntrls;
	
	public ControlGetterInputGroup(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);				
	}
	
	@Override
	public Control getControl() {
		return 
			new ControlGroup(getName(), driver, findBy)
				.addElements(cntrls, new InputGroup(findBy));
	}

	@Override
	public ControlGetterGroup addControls(List<ControlGetter> cntrls) {
		this.cntrls = cntrls;
		return this;
	}
	
}
