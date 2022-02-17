/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

import controls.Control;
import controls.TextSelect;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * This was added for InitialisePayroll.
 * Where there are select boxes that cannot select,
 * or don't have a list to select from.
 * 
 */
public final class ControlGetterSelect extends ControlGetter {
	private Control cntrl;
		
	public ControlGetterSelect(CoreData coreData, By findBy) {
		super(coreData, findBy);
		
		cntrl = new TextSelect(super.driver, super.findBy);
	}

	@Override
	public Control getControl() {
		return cntrl;
	}
	
}
