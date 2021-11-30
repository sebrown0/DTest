/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

import controls.Control;
import object_models.dk_grid.DkGridEmployeeDetails;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterDkGridEmployeeDetails extends ControlGetter {
	
	public ControlGetterDkGridEmployeeDetails(CoreData coreData, By findBy) {
		super(coreData, findBy);
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DkGridEmployeeDetails(super.coreData);
	}

}
