/**
 * 
 */
package object_models.date_picker;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 * Date picker found on a page, i.e. from a combo box.
 */
public class DatePickerPage extends DatePicker{
		
	public DatePickerPage(WebDriver driver) {
		super(driver);	
	}
		
	@Override
	protected void mapSelectors() {
		List<WebElement> dateSelectorsList = driver.findElements(By.cssSelector("input[title='Date selector']"));
		String datePickerName;
		for (WebElement webElement : dateSelectorsList) {
			datePickerName = webElement.getAttribute("placeholder");
			logger.debug("Found date picker [ " + datePickerName + "]");
			dateSelectorsMap.put(datePickerName, webElement);
		}
	}	
}
