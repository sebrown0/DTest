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
public class TitleModalFadeShow extends Title{	
	public TitleModalFadeShow(String expectedTitle, WebDriver driver) {
		super(expectedTitle, driver, By.className("modal-title"));
	}
}