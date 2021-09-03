/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class IFrame {
	private WebDriver driver;	
	private String title;
	
	public IFrame(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;
	}
		
	public void switchUsingTitle() {
		driver.switchTo().frame(driver.findElement(byCssUsingTitle()));
	}
	
	private By byCssUsingTitle() {
		return By.cssSelector("iframe[title='" + title + "']");
	}
}
