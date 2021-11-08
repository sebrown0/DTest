/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.ComboWriteAndSelect;
import controls.Control;

/**
 * @author Steve Brown
 *
 */
public class ControlGetterComboWriteAndSelect extends ControlGetter {
	private By resultsBy;
	
	public ControlGetterComboWriteAndSelect(WebDriver driver, By findBy, By resultsBy) {
		super(driver, findBy);
		this.resultsBy = resultsBy;
	}

	@Override
	public Control getControl() {
		return new ComboWriteAndSelect(driver, findBy, resultsBy);
	}

}
