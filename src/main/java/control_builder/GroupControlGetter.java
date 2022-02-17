/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.ControlGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Return a new instance of a control, i.e. TextOut.
 */
public abstract class GroupControlGetter {
	protected CoreData coreData;
	protected WebDriver driver;
	protected By findBy;
	
	public GroupControlGetter(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
	}
	
	public GroupControlGetter(CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.findBy = findBy;
	}

	public abstract ControlGroup getControl();
}
