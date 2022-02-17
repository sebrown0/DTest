/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import controls.Control;
import controls.InputGroup;
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
	
	public ControlGetterInputGroup(String name, CoreData coreData) {
		super(name, coreData);		
	}

	@Override
	public Control getControl() {
		return new InputGroup(findBy).addElements(cntrls);
	}

	@Override
	public ControlGetterGroup addControls(List<ControlGetter> cntrls) {
		this.cntrls = cntrls;
		return this;
	}
	
}
