/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.Control;
import controls.TextOut;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class ControlGetterTextOut extends ControlGetter {
	public ControlGetterTextOut(WebDriver driver, By findBy) {
		super(driver, findBy);		
	}

	@Override
	public Control getControl() {
		return new TextOut(driver, findBy);
	}

}
