/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Return a new instance of a control, i.e. TextOut.
 */
public abstract class ControlGetter {
	protected WebDriver driver;
	protected By findBy;
	
	public ControlGetter(WebDriver driver) {
		this.driver = driver;
	}
	
	public ControlGetter(WebDriver driver, By findBy) {
		this.driver = driver;
		this.findBy = findBy;
	}

	protected abstract Control getControl();
}
