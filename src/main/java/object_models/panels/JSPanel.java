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

import object_models.helpers.PageTitle;
import object_models.helpers.TitlePanel;

/**
 * @author SteveBrown
 *
 */
public class JSPanel  {
	private WebDriver driver;
	private PageTitle title;
	
	public static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
	
	public JSPanel(WebDriver driver, String expectedTitle) {
		this.driver = driver;
		this.title = new TitlePanel(expectedTitle, driver);
		
		waitForLoad();
	}

	public void waitForLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.attributeContains(By.className("jsPanel-title"), "innerHTML", expectedTitle));
		wait.until(ExpectedConditions.attributeContains(TITLE_SELECTOR, "innerHTML", title.getExpected()));		
	}
		
	public void close() {
		driver.switchTo().defaultContent();
		WebElement we = driver.findElement(By.cssSelector(".jsPanel-btn.jsPanel-btn-close"));
		we.click();		
	}

	public PageTitle getTitle() {
		return title;
	}
}
