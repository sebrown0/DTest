/**
 * 
 */
package object_models.helpers.title;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class TitleModalForm extends Title{	
	public TitleModalForm(String expectedTitle, WebDriver driver) {
		super(expectedTitle, driver, By.cssSelector("body > div.modal.show > div > div > div.modal-header > h5"));
	}
}