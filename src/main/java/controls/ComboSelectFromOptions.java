/**
 * 
 */
package controls;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 * Can only select an item from an existing list.
 * 
 */
public class ComboSelectFromOptions extends Combo {

	public ComboSelectFromOptions(WebDriver driver, WebElement combo) {
		super(driver, combo);	
	}

}
