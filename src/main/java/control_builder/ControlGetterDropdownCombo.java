/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import controls.Control;
import object_models.controls.DropdownCombo;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class ControlGetterDropdownCombo extends ControlGetter {	
	private ContextManager contextManager;
	
	public ControlGetterDropdownCombo(WebDriver driver, By findBy, ContextManager contextManager) {
		super(driver, findBy);
		this.contextManager = contextManager;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DropdownCombo(driver, contextManager);
	}

}
