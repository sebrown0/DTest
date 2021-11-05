/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.controls.EmployeeSelection;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterEmployeeSelection extends ControlGetter {	
	private ContextManager contextManager;
	
	public ControlGetterEmployeeSelection(WebDriver driver, By findBy, ContextManager contextManager) {
		super(driver, findBy);
		this.contextManager = contextManager;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new EmployeeSelection(driver, contextManager);
	}

}
