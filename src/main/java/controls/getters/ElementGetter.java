/**
 * 
 */
package controls.getters;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementGetter {
	private WebDriverWait wait;
	private Logger logger = LogManager.getLogger(ElementGetter.class);
	
	public ElementGetter(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}
	
	public WebElement getElementIfClickable(Control cntrl) {
		WebElement res = null;
		WebElement cntrlEl = cntrl.getElement();
		By locator = cntrl.getLocator();
		
		if(cntrlEl == null && locator != null) {
			res = getElementIfClickable(locator);
		}else if(cntrlEl != null){
			res = getElementIfClickable(cntrlEl);
		}		
		
		return res;
	}

	private WebElement getElementIfClickable(By locator) {		
		WebElement res = null;
		try {
			res = wait.until(ExpectedConditions.elementToBeClickable(locator));
		}catch (TimeoutException e) {
			logger.error("Unable to find element [" + e + "]");		
		}					
		return res;
	}
	
	private WebElement getElementIfClickable(WebElement el) {		
		WebElement res = null;
		try {
			res = wait.until(ExpectedConditions.elementToBeClickable(el));
		}catch (TimeoutException e) {
			logger.error("Unable to find element [" + e + "]");		
		}					
		return res;
	}
	
}
