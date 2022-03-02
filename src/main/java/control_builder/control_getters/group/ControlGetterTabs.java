/**
 * 
 */
package control_builder.control_getters.group;

import org.openqa.selenium.By;

import controls.adders.TabGroupAdder;
import controls.interfaces.Control;
import dynamic_tests.elements.ControlGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public final class ControlGetterTabs extends ControlGetterGroup {
	
	public ControlGetterTabs(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);				
	}
	
	@Override
	public Control getControl() {
		return 
			new ControlGroup(driver, findBy)
				.addElements(cntrls, new TabGroupAdder());
	}

}
