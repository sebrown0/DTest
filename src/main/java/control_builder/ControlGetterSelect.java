/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.Control;
import controls.TextSelect;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * This was added for InitialisePayroll.
 * Where there are select boxes that cannot select,
 * or don't have a list to select from.
 * 
 */
public final class ControlGetterSelect extends ControlGetter {
	public ControlGetterSelect(WebDriver driver, By findBy) {
		super(driver, findBy);		
	}

	@Override
	public Control getControl() {
		return new TextSelect(driver, findBy);
	}

}
