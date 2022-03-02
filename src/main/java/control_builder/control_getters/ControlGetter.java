/**
 * 
 */
package control_builder.control_getters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.interfaces.Control;
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
	protected By findParentBy;
	
	private String name;
	
	public ControlGetter(String name, CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.name = name;
	}
	
	public ControlGetter(String name, CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.findBy = findBy;
		this.name = name;
	}
	
	public abstract Control getControl();	
	
	public ControlGetter setParent(By findParentBy) {
		this.findParentBy = findParentBy;
		return this;
	}

	public String getName() {
		return name;
	}
}
