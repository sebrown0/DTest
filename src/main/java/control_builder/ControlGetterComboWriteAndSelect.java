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

	public ControlGetterComboWriteAndSelect(WebDriver driver, By findBy) {
		super(driver, findBy);

	}

	@Override
	public Control getControl() {
		return new ComboWriteAndSelect(driver, findBy);
	}

}
