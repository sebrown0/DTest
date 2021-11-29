/**
 * 
 */
package controls;

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
 *
 */
public class Button implements Control {
	private WebDriver driver;
	private By btnLocator;
	
	public Button(WebDriver driver, By btnLocator) {
		this.driver = driver;
		this.btnLocator = btnLocator;
	}

	public void click() {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnLocator));
			btn.click();	
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to click btn [" + e + "]");
		}		
	}
}
