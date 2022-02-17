/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.Control;
import object_models.controls.DropdownCombo;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class ControlGetterDropdownCombo extends ControlGetter {	
	public ControlGetterDropdownCombo(CoreData coreData, By findBy) {
		super(coreData, findBy);
	}

	@Override
	public Control getControl() {
		super.driver.findElement(findBy).click();
		return new DropdownCombo(super.coreData);
	}

	@Override
	public ControlGetter setElement(WebElement el) {
		System.out.println("ControlGetterDropdownCombo.setElement ** NOT IMPLEMENTED **"); // TODO - Implement 	
		return null;
	}
}
