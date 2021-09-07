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
	private String title;
	private WebElement iFrame;
	
	public IFrame(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;
	}
		
	public IFrame switchUsingTitle() {
		iFrame = driver.findElement(byCssUsingTitle());
		driver.switchTo().frame(iFrame);
		return this;
	}
	
	public WebElement getIFrameElement() {
		return iFrame;
	}
	
	private By byCssUsingTitle() {
		return By.cssSelector("iframe[title='" + title + "']");
	}
}
