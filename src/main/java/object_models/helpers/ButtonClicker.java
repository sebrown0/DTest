/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Steve Brown
 *
 * Click a button for different scenarios
 */
public final class ButtonClicker {
	
	/*
	 * Click until the btn is not visible for a max number of tries.
	 * Useful when closing a form. 
	 */
	public static void clickUntilNotVisible(WebDriver driver, By btnLocator, int maxTries) {
		try {
			WebElement btn = driver.findElement(btnLocator);
			while(ExpectedConditions.visibilityOfElementLocated(btnLocator) != null && --maxTries > 0) {
				clickBtn(btn);
			}	
		} catch (Exception e) {
			// We have reached the required state or the button was never there, etc..
		}
		
	}
	
	private static void clickBtn(WebElement btn) throws Exception {
		btn.click();	
	}
}
