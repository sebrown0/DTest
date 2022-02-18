/**
 * 
 */
package control_builder.control_getters;

import org.openqa.selenium.By;

import controls.Control;
import object_models.dk_grid.DkGridEmployeeDetails;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterDkGridEmployeeDetails extends ControlGetter {
	
	public ControlGetterDkGridEmployeeDetails(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DkGridEmployeeDetails(super.coreData, findBy);
	}

}
