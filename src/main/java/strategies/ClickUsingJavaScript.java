/**
 * 
 */
package strategies;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class ClickUsingJavaScript {
	public static void performClick(WebDriver driver, String jsString) {		
		runJavaScript(driver, jsString);
	}
	
	public static void performClick(WebDriver driver, WebElement element) {		
		runJavaScript(driver, element.getAttribute("onclick"));
	}
	
	public static void performClick(WebDriver driver, By locator) {
		runJavaScript(driver, driver.findElement(locator).getAttribute("onclick"));		
	}
	
	private static void runJavaScript(WebDriver driver, String jsString) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		if (jsString != null) {
			jsString.replaceAll("javascript:", "");
			jsExecutor.executeScript(jsString);
		}
	}
}
