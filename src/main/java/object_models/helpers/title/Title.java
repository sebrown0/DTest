/**
 * 
 */
package object_models.helpers.title;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Steve Brown
 *
 */
public class Title implements PageTitle{
	private String expectedTitle;	
	private WebDriver driver;
	private By titleSelector;
	private String actualTitle;
	
	public Title(String expectedTitle, WebDriver driver, By titleSelector) {
		this.expectedTitle = expectedTitle;
		this.driver = driver;
		this.titleSelector = titleSelector;
		this.setActualTitle();
	}

	@Override
	public String getExpected() {
		return expectedTitle;
	}

	@Override
	public String getActual() {
		return actualTitle;		
	}		
	
	private void setActualTitle() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(titleSelector));
		WebElement e = driver.findElement(titleSelector);		
		actualTitle = checkElement(e);
	}
	
	private String checkElement(WebElement e) {
		if(e == null) {
			LogManager.getLogger().error("Web element for selector [" + titleSelector.toString() + "] is null");
			return null;
		}else {			
			return e.getAttribute("textContent").trim();
		}
	}
}
