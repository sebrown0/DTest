/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	private By resultsBy;
	private TextWriter writer;
	
	public ControlGetterComboWriteAndSelect(CoreData coreData, By findBy, By resultsBy, TextWriter writer) {
		super(coreData, findBy);
		
		this.combo = new ComboWriteAndSelect(coreData, findBy, resultsBy, writer);
	}
	
	public ControlGetterComboWriteAndSelect(CoreData coreData, By resultsBy, TextWriter writer) {
		super(coreData);
		
		this.resultsBy = resultsBy;
		this.writer = writer;
	}

	@Override
	public Control getControl() {
		return combo;
	}

	@Override
	public ControlGetter setElement(WebElement el) {
		this.combo = new ComboWriteAndSelect(coreData, el, resultsBy, writer);
		return this;
	}

}
