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
 * 	Initial
 * @version 1.1
 *  Add isAvailable().
 * @since 1.0
 *
 */
public class Button implements Control {
	private WebDriver driver;
	private By btnLocator;
	private WebElement btn;
	
	public Button(WebDriver driver, By btnLocator) {
		this.driver = driver;
		this.btnLocator = btnLocator;
	}

	public void click() {		
		if(isAvailable()) {
			btn.click();
		}
	}

	@Override
	public boolean isAvailable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			btn = wait.until(ExpectedConditions.elementToBeClickable(btnLocator));
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to find btn [" + e + "]");
			return false;
		}		
	}
	
}
