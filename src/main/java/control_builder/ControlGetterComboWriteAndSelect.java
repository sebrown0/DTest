/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.ComboWriteAndSelect;
import controls.Control;
import object_models.helpers.text_writer.TextWriter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterComboWriteAndSelect extends ControlGetter {
	private Control combo;
	
	public ControlGetterComboWriteAndSelect(WebDriver driver, By findBy, By resultsBy, TextWriter writer) {
		super(driver, findBy);
		
		this.combo = new ComboWriteAndSelect(driver, findBy, resultsBy, writer);
	}

	@Override
	public Control getControl() {
		return combo;
	}

}
