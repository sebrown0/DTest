/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import org.openqa.selenium.By;

import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class ControlGetterGroup extends ControlGetter {

	public ControlGetterGroup(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);				
	}
	
	public abstract ControlGetterGroup addControls(List<ControlGetter> cntrls);	
}
