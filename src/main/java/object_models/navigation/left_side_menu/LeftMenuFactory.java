/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SteveBrown
 *
 */
public class LeftMenuFactory {
	private WebDriver driver;
		
	public LeftMenuFactory(WebDriver driver) {
		this.driver = driver;
	}
		
	public List<WebElement> getList() {
		LogManager.getLogger().info("Getting list of menu items");				
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement navAccordion = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-accordion")));		
		
		return navAccordion.findElements(By.tagName("a"));
	}	
}
