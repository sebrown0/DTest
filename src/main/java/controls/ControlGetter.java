/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * Return a new instance of a control, i.e. TextOut.
 */
public abstract class ControlGetter {
	protected WebDriver driver;
	protected By findBy;
	
	public ControlGetter(WebDriver driver, By findBy) {
		this.driver = driver;
		this.findBy = findBy;
	}

	protected abstract Control getControl();
}
