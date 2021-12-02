/**
 * 
 */
package object_models.modules.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import exceptions.ElementDoesNotExistException;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add isCurrentModule(...)
 * @version 1.2 
 * 	Add isValidModuleName(...)
 * @since 1.0
 *
 */
public class ModuleChecker {
	private static final By MOD_NAME_LOCATOR = By.cssSelector("body>form>header>div>div");

	public static  boolean isValidModuleName(String moduleName) {
		return moduleName != null;
	}
	public static boolean isCurrentModule(String modName, WebDriver driver) {
		try {
			return getCurrentModule(driver).equals(modName);
		} catch (Exception e) {
			new ElementDoesNotExistException("Error getting current module name");
			return false;
		}
	}
	
	private static String getCurrentModule(WebDriver driver) {
		WebElement element = driver.findElement(MOD_NAME_LOCATOR);		
		return element.getAttribute("textContent");
	}	
}
