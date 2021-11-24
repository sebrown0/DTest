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

import context_manager.states.StateIframe;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class IFrame {
	private WebDriver driver;
	private WebElement iFrame;
	private By byLocator;
	private WebDriverWait wait;
	
	public IFrame(WebDriver driver, By byLocator) {
		this.driver = driver;
		this.byLocator = byLocator;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
		
	public IFrame switchUsingLocator(StateIframe stateIframe) {		
		try {			 	
			iFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			driver.switchTo().frame(iFrame); 	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not switch to iFrame");
		}		 	
		return this;
	}
		
	public WebElement getIFrameElement() {
		return iFrame;
	}
	
}
