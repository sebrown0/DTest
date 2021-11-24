/**
 * 
 */
package object_models.helpers;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DriverWait {
	
	/*
	 * Use default wait.
	 */
	public static WebElement getElementAfterWait(WebDriver driver, By byLocator) {		
		return getElementAfterWait(driver, byLocator, Duration.ofSeconds(5));
	}
	
	/*
	 * Wait for the specified duration.
	 */
	public static WebElement getElementAfterWait(WebDriver driver, By byLocator, Duration duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		WebElement result = null;
		try {
			result = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		} catch (Exception e) {
			LogManager.getLogger().error("Failed to get element after wait");
		}
		return result;
	}
}