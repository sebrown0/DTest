/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	@Override
	public ControlGetter setElement(WebElement el) {
		System.out.println("ControlGetterEmployeeSelection.setElement ** NOT IMPLEMENTED **"); // TODO - Implement 	
		return null;
	}
}
