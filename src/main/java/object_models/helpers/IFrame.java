/**
 * 
 */
package object_models.helpers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.states.StateIframe;

/**
 * @author Steve Brown
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
		/*
		 * Have to be careful that we're going to the correct IFrame.
		 *  
		 * WHEN DO WE WANT TO GO BACK TO DEFAULT.
		 * 	-> WHEN UNLOADING/CLOSING FORM/PANEL.
		 * 
		 * WHEN DO WE not WANT TO GO BACK TO DEFAULT.
		 * 	-> WHEN LOADING FORM/PANEL
		 */
		iFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));	
		driver.switchTo().frame(iFrame);				
		return this;
	}
		
	public WebElement getIFrameElement() {
		return iFrame;
	}
	
//	public WebElement getElementFromIframe(By byLocator) {
//		switchUsingLocator();
//		return iFrame.findElement(byLocator);
//	}
}
