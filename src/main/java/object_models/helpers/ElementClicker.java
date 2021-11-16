/**
 * 
 */
package object_models.helpers;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import controls.Button;
import controls.Control;
import controls.ControlName;
import object_models.panels.JsPanelControl;

/**
 * @author Steve Brown
 *
 * Click a button for different scenarios
 */
public final class ElementClicker {
	
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
	
	public static Optional<Control> clickButton(ControlName cntrlName, JsPanelControl panelControl){
		Optional<Control> pgCntrl = panelControl.getControl(cntrlName);
		
		pgCntrl.ifPresent(cntrl -> {
			Button btn = (Button) cntrl;
			btn.click();			
		});
		
		return pgCntrl;
	}
	
	/*
	 * Vanilla click.
	 */
	private static void clickBtn(WebElement btn) throws Exception {
		btn.click();	
	}
	
	/*
	 * Try clicking an element for maxAttempts.
	 * This is good for when the driver would normally throw 
	 * a stale element exception.
	 */
	public static boolean tryClickingElement(WebDriver driver, By elementLocator) {		
		int maxAttempts = 50;
		boolean clickedOk = false;
		
		while (--maxAttempts > 0) {
			try {
				driver.findElement(elementLocator).click();
				clickedOk = true;
				break;
			} catch (Exception e) {		}
		}			
		return clickedOk;
	}
	
}
