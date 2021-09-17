/**
 * 
 */
package object_models.helpers.closers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class CloserModalForm extends Closer {
	private static final By LOCATOR = 
			By.cssSelector("body > div.modal.show > div > div > div.modal-header > button");
	
	public CloserModalForm(WebDriver driver) {
		super(driver, LOCATOR);
	}
}

