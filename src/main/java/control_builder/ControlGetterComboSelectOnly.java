/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

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
	private By resultsBy;
	
	public ControlGetterComboSelectOnly(CoreData coreData, By findBy, By resultsBy) {
		super(coreData, findBy);
		this.resultsBy = resultsBy;
	}

	@Override
	public Control getControl() {
		return new ComboSelectFromList(coreData, findBy, resultsBy);
	}

}
