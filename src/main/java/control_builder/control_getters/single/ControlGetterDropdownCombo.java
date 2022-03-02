/**
 * 
 */
package control_builder.control_getters.single;

import org.openqa.selenium.By;

import control_builder.control_getters.ControlGetter;
import controls.interfaces.Control;
import object_models.modal_forms.DropdownCombo;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class ControlGetterDropdownCombo extends ControlGetter {	
	public ControlGetterDropdownCombo(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);
	}

	@Override
	public Control getControl() {
		super.driver.findElement(findBy).click();
		return new DropdownCombo(super.coreData, findBy);
	}

}
