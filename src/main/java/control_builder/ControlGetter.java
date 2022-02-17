/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.Control;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Return a new instance of a control, i.e. TextOut.
 */
public abstract class ControlGetter {
	protected CoreData coreData;
	protected WebDriver driver;
	protected By findBy;
	
	public ControlGetter(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
	}
	
	public ControlGetter(CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.findBy = findBy;
	}

	public abstract Control getControl();
	public abstract ControlGetter setElement(WebElement el);
}
