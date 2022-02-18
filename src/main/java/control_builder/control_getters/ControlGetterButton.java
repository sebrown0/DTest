/**
 * 
 */
package control_builder.control_getters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.Button;
import controls.Control;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterButton extends ControlGetter {
	private Control btn;
	
	public ControlGetterButton(String name, CoreData coreData, By findBy) {
		super(name, coreData, findBy);
		
		this.btn = new Button(driver, findBy);
	}
	
	public ControlGetterButton(String name, CoreData coreData, By findBy, WebElement elButton) {
		super(name, coreData);
		
		this.btn = new Button(coreData.getWebDriver(), findBy, elButton);
	}
	
	@Override
	public Control getControl() {
		return btn;
	}

}
