/**
 * 
 */
package control_builder;

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
	
	public ControlGetterButton(CoreData coreData, By findBy) {
		super(coreData, findBy);
		
		this.btn = new Button(driver, findBy);
	}
	public ControlGetterButton(CoreData coreData, By findBy, WebElement elButton) {
		super(coreData, findBy);
		
		this.btn = new Button(elButton);
	}

	@Override
	public Control getControl() {
		return btn;
	}

}
