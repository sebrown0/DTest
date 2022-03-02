/**
 * 
 */
package control_builder.control_getters.single;

import org.openqa.selenium.By;

import control_builder.control_getters.ControlGetter;
import controls.interfaces.Control;
import controls.with_text.Label;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *  
 */
public final class ControlGetterLabel extends ControlGetter {
	private Control cntrl;
		
	public ControlGetterLabel(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);
		
		cntrl = new Label(super.driver, super.findBy);
	}

	@Override
	public Control getControl() {
		return cntrl;
	}
	
}
