/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public abstract class ControlGetterGroup extends ControlGetter {

	public ControlGetterGroup(String name, CoreData coreData) {
		super(name, coreData);
	}
	
	public abstract ControlGetterGroup addControls(List<ControlGetter> cntrls);	
}
