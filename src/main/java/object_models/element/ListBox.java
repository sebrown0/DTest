/**
 * 
 */
package object_models.element;

import java.time.Duration;
import java.util.List;

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
	private List<WebElement> listItems;
	
	public ListBox(WebDriver driver, By findBy) {
		this.driver = driver;
		this.findBy = findBy;

		populateList();
	}
	
	private void populateList() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		listItems = results.findElements(By.cssSelector("li[role='option']"));
	}
	
	public WebElement getListItem(String item) {
		for (WebElement e : listItems) {
			String itemValue = e.getText();
			if(itemValue.equals(item)) {
				return e;
			} 	
		}
		return null;
	}
	
}
