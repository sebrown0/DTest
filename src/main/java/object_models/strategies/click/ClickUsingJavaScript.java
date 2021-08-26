/**
 * 
 */
package object_models.strategies.click;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class ClickUsingJavaScript {
	public static void performClick(WebDriver driver, By locator) {
		String jsString =	driver.findElement(locator).getAttribute("onclick");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		if (jsString != null) {
			jsString.replaceAll("javascript:", "");
			jsExecutor.executeScript(jsString);
		}
	}
}
