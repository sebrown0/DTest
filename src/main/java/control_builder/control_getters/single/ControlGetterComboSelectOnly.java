/**
 * 
 */
package control_builder.control_getters.single;

import org.openqa.selenium.By;

import control_builder.control_getters.ControlGetter;
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
		
	public ControlGetterComboSelectOnly(String name, CoreData coreData, By findBy, By resultsBy) {
		super(name, coreData, findBy);
		
		this.cntrl = new ComboSelectFromList(coreData, findBy, resultsBy);
	}
	
	@Override
	public Control getControl() {
		return cntrl;
	}

}
