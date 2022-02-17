/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.Button;
import controls.Control;
import controls.Tab;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterTab extends ControlGetter {
	private Control tab;
	
	public ControlGetterTab(CoreData coreData, By findBy) {
		super(coreData, findBy);
		
		this.tab = new Button(driver, findBy);
	}
	public ControlGetterTab(CoreData coreData, By findBy, WebElement elTab) {
		super(coreData, findBy);
		
		this.tab = new Tab(coreData.getWebDriver(), elTab);
	}

	@Override
	public Control getControl() {
		return tab;
	}

}
