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
public final class ControlGetterEmployeeSelection implements ControlGetter {
	private WebDriver driver;
	private By findBy;
	private ContextManager contextManager;
	
	public ControlGetterEmployeeSelection(WebDriver driver, By findBy, ContextManager contextManager) {
		this.driver = driver;
		this.findBy = findBy;
		this.contextManager = contextManager;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new EmployeeSelection(driver, contextManager);
	}

}
