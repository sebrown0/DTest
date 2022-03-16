/**
 * 
 */
package control_builder.control_getters.single;

import org.openqa.selenium.By;

import control_builder.control_getters.ControlGetter;
import controls.interfaces.Control;
import object_models.modal_forms.emp_selection.EmployeeSelection;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class ControlGetterEmployeeSelection extends ControlGetter {	
	private static final String name = "EmployeeSelection";
	private static final By locator = By.cssSelector("div[class='modal show']");
	
	public ControlGetterEmployeeSelection(CoreData coreData) {
		super(name, coreData, locator);
	}

	@Override
	public Control getControl() {
//		super.driver.findElement(findBy).click();
		return new EmployeeSelection(super.coreData, findBy);
	}
	
}
