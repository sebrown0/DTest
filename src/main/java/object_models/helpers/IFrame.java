/**
 * 
 */
package object_models.helpers;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.states.StateIframe;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	1. Remove getIFrameElement().
 *  2. Change expected conditions.
 * @since 1.0
 *
 */
public class IFrame {
	private WebDriver driver;	
	private By byLocator;
	private WebDriverWait wait;
	
	public IFrame(WebDriver driver, By byLocator) {
		this.driver = driver;
		this.byLocator = byLocator;		
	}
		
	public IFrame switchUsingLocator(StateIframe stateIframe) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {			 	
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byLocator)); 	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not switch to iFrame [" + e + "]"); 	
		}		 	
		return this;
	}		
	
}
