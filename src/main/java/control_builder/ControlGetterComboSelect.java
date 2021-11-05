/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.ComboSelect;
import controls.Control;

/**
 * @author SteveBrown
 *
 */
public class ControlGetterComboSelect extends ControlGetter {

	public ControlGetterComboSelect(WebDriver driver, By findBy) {
		super(driver, findBy);

	}

	@Override
	public Control getControl() {
		return new ComboSelect(driver, findBy);
	}

}
