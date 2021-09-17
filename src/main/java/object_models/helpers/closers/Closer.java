/**
 * 
 */
package object_models.helpers.closers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Steve Brown
 *
 * Close an element found using byLocator.
 */
public class Closer implements Closable {
	private WebDriver driver;
	private By byLocator;
	
	public Closer(WebDriver driver, By byLocator) {
		this.driver = driver;
		this.byLocator = byLocator;
	}

	@Override
	public void close() throws Exception {
		driver.switchTo().defaultContent();
		WebElement we = driver.findElement(byLocator);
		we.click();		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOf(we));				
	}
	
}
