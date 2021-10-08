/**
 * 
 */
package object_models.controls;

import org.openqa.selenium.WebDriver;

import controls.Control;
import object_models.forms.FormModal;

/**
 * @author SteveBrown
 *
 */
public class DropdownCombo extends FormModal implements Control {

	public DropdownCombo(WebDriver driver, String expectedTitle) {
		super(driver, expectedTitle);
		// TODO Auto-generated constructor stub
	}

}
