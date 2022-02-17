/**
 * 
 */
package control_builder.control_getters;

import org.openqa.selenium.By;

import controls.Control;
import object_models.controls.EmployeeSelection;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterEmployeeSelection extends ControlGetter {	
	
	public ControlGetterEmployeeSelection(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);
	}

	@Override
	public Control getControl() {
		super.driver.findElement(findBy).click();
		return new EmployeeSelection(super.coreData);
	}
	
}
