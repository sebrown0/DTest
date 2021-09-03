/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Steve Brown
 *
 */
public class Jquery {
	
	public static void goToElement(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().build().perform();
		
	}
	
}
