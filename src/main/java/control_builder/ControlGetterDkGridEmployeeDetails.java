/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import controls.Control;
import object_models.dk_grid.DkGridEmployeeDetails;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterDkGridEmployeeDetails extends ControlGetter {
	private ContextManager contextManager;
	
	public ControlGetterDkGridEmployeeDetails(WebDriver driver, By findBy, ContextManager contextManager) {
		super(driver, findBy);
		this.contextManager = contextManager;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DkGridEmployeeDetails(driver, contextManager);
	}

}
