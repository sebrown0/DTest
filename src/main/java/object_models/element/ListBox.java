/**
 * 
 */
package object_models.element;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

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
public class ListBox {
	private By findBy;
	private WebDriver driver;
	private WebElement results;
	private List<WebElement> listItems;
	
	public ListBox(WebDriver driver, By findBy) {
		this.driver = driver;
		this.findBy = findBy;
	}
	
	private void populateList() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		results = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		listItems = results.findElements(By.cssSelector("li[role='option']"));
	}
	
	public WebElement getListItem(String item) {
		populateList();
		for (WebElement e : listItems) {
			String itemValue = e.getText();
			if(itemValue.equals(item)) {
				return e;
			} 	
		}
		return null;
	}
	
	public Optional<String> getAlert() {
		Optional<String> alertVal = Optional.empty();
		
		try {
			WebElement alert = driver.findElement(By.cssSelector("li[role='alert']"));
			alertVal = Optional.ofNullable(alert.getText());
		} catch (Exception e) {
			LogManager.getLogger().debug("Could not get alert for dropdown. Maybe it doesn't exist?");
		}
		return alertVal;
	}
}
