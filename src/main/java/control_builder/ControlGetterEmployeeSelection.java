/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

import controls.Control;
import object_models.controls.EmployeeSelection;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterEmployeeSelection extends ControlGetter {	
	
	public ControlGetterEmployeeSelection(CoreData coreData, By findBy) {
		super(coreData, findBy);
	}

	@Override
	public Control getControl() {
		super.driver.findElement(findBy).click();
		return new EmployeeSelection(super.coreData);
	}

}
