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
 */
public class CloserPanel implements Closable{
	private WebDriver driver;
	
	public CloserPanel(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void close() {
		By byLocator = By.cssSelector(".jsPanel-btn.jsPanel-btn-close");
		driver.switchTo().defaultContent();
		WebElement we = driver.findElement(byLocator);
		we.click();		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOf(we));		
	}
}
