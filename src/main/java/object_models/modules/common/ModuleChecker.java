/**
 * 
 */
package object_models.modules.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class ModuleChecker {
	private static final By MOD_NAME_LOCATOR = By.cssSelector("body>form>header>div>div");

	public static String getCurrentModule(WebDriver driver) {
		WebElement element = driver.findElement(MOD_NAME_LOCATOR);		
		return element.getAttribute("textContent");
	}	
}
