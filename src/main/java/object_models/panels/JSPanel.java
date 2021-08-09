/**
 * 
 */
package object_models.panels;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.helpers.Title;

/**
 * @author SteveBrown
 *
 */
public class JSPanel {
	private WebDriver driver;
	private Title title;
	
	public JSPanel(WebDriver driver, Title title) {
		this.driver = driver;
		this.title = title;
		waitForLoad();
	}

	public void waitForLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.className("jsPanel-title"), "innerHTML", title.getExpectedTitle()));		
	}
		
	public void close() {
		driver.switchTo().defaultContent();
		WebElement we = driver.findElement(By.cssSelector(".jsPanel-btn.jsPanel-btn-close"));
		we.click();		
	}
}
