/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

import controls.Control;
import controls.TextOut;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class ControlGetterTextOut extends ControlGetter {
	public ControlGetterTextOut(CoreData coreData, By findBy) {
		super(coreData, findBy);		
	}

	@Override
	public Control getControl() {
		return new TextOut(super.driver, super.findBy);
	}

}
