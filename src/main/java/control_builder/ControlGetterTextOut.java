/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.Control;
import controls.TextOut;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterTextOut extends ControlGetter {
	private WebDriver driver;
	private By findBy;
	
	public ControlGetterTextOut(WebDriver driver, By findBy) {
		super(driver, findBy);
	}

	@Override
	public Control getControl() {
		return new TextOut(driver, findBy);
	}

}
