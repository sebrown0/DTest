/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		iFrame = driver.findElement(byLocator);	
		driver.switchTo().frame(iFrame);	
		return this;
	}
		
	public WebElement getIFrameElement() {
		return iFrame;
	}
}
