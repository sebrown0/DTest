/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.TextOut;

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
