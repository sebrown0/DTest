/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

import controls.Button;
import controls.Control;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterButton extends ControlGetter {
	private Control btn;
	
	public ControlGetterButton(CoreData coreData, By findBy) {
		super(coreData, findBy);
		
		this.btn = new Button(driver, findBy);
	}

	@Override
	public Control getControl() {
		return btn;
	}

}
