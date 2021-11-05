/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.ComboSelect;

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
