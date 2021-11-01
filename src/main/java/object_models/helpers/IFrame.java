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

/**
 * @author Steve Brown
 *
 */
public class IFrame {
	private WebDriver driver;
	private WebElement iFrame;
	private By byLocator;
		
	public IFrame(WebDriver driver, By byLocator) {
		this.driver = driver;
		this.byLocator = byLocator;
	}
		
	public IFrame switchUsingLocator() {
		try {
			driver.findElement(By.id("corners"));	
		} catch (Exception e) {
			/*
			 * CANNOT GET THE iFRAME WITHOUT GETTING THIS ELEMENT FIRST.
			 * EVEN THOUGH AN EXCEPITION IS THROWN.
			 * DON'T KNOW WHY!
			 */ 	
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		iFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));	
		driver.switchTo().frame(iFrame);
		
		return this;
	}
		
	public WebElement getIFrameElement() {
		return iFrame;
	}
}
