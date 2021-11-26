/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.ComboSelectFromList;
import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterComboSelectOnly extends ControlGetter {
	private By resultsBy;
	
	public ControlGetterComboSelectOnly(WebDriver driver, By findBy, By resultsBy) {
		super(driver, findBy);
		this.resultsBy = resultsBy;
	}

	@Override
	public Control getControl() {
		return new ComboSelectFromList(driver, findBy, resultsBy);
	}

}
