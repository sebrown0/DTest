/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.Control;
import controls.TextOut;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class ControlGetterTextOut extends ControlGetter {
	private Control cntrl;
	
	public ControlGetterTextOut(CoreData coreData, By findBy) {
		super(coreData, findBy);
		this.cntrl = new TextOut(super.driver, super.findBy);
	}

	@Override
	public Control getControl() {
		return cntrl;
	}

	@Override
	public ControlGetter setElement(WebElement el) {
		this.cntrl = new TextOut(el);
		return this;
	}

}
