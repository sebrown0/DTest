/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.ComboSelectFromList;
import controls.Control;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterComboSelectOnly extends ControlGetter {
	private Control cntrl;
	
	public ControlGetterComboSelectOnly(CoreData coreData) {
		super(coreData);
	}
	
	public ControlGetterComboSelectOnly(CoreData coreData, By findBy, By resultsBy) {
		super(coreData, findBy);
		
		this.cntrl = new ComboSelectFromList(coreData, findBy, resultsBy);
	}

	@Override
	public ControlGetter setElement(WebElement el) {
		/*
		 * DO WE NEED resultsBy !!!!!!!!!!!!!!!!!!!!!!!!!!
		 */
		this.cntrl = new ComboSelectFromList(coreData, el);
		return this;
	}
	
	@Override
	public Control getControl() {
		return cntrl;
	}

}
