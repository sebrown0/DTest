/**
 * 
 */
package control_builder.control_getters;

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
public class ControlGetterInputGroup extends ControlGetter {
	private Control grp;
	
	public ControlGetterInputGroup(String name, CoreData coreData, InputGroup grp) {
		super(name, grp.getCoreData(), grp.getFindBy());
		
		this.grp = grp;
	}

	@Override
	public Control getControl() {
		return grp;
	}
	
}
