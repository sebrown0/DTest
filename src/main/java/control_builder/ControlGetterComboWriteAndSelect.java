/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;

import controls.ComboWriteAndSelect;
import controls.Control;
import object_models.helpers.text_writer.TextWriter;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterComboWriteAndSelect extends ControlGetter {
	private Control combo;
	
	public ControlGetterComboWriteAndSelect(CoreData coreData, By findBy, By resultsBy, TextWriter writer) {
		super(coreData, findBy);
		
		this.combo = new ComboWriteAndSelect(coreData, findBy, resultsBy, writer);
	}

	@Override
	public Control getControl() {
		return combo;
	}

}
