/**
 * 
 */
package object_models.date_picker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import object_models.element.InputWriter;



/**
 * @author Steve Brown
 *
 */
public class DatePickerPage {
	private WebDriver driver;
	private Map<String, WebElement> dateSelectorsMap = new HashMap<>();
	private Logger logger = LogManager.getLogger();
	private WebElement date;
	
	
	@FindBy(css = "input[title='Date selector']")
  private List<WebElement> dateSelectorsList;
	
	public DatePickerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		mapSelectors();
	}
	
	private void mapSelectors() {
		String datePickerName;
		for (WebElement webElement : dateSelectorsList) {
			datePickerName = webElement.getAttribute("placeholder");
			logger.debug("Found date picker [ " + datePickerName + "]");
			dateSelectorsMap.put(datePickerName, webElement);
		}
	}
	
	public DatePickerDay getDatePicker(String pickerName) {
		date = dateSelectorsMap.get(pickerName);
		date.click();
  	return new DatePickerDay(driver);
	}
		
	public void writeDate(String pickerName, InputWriter writer, String dateStr) {
		DatePickerDay datePickerDay = new DatePickerDay(driver);
		date = dateSelectorsMap.get(pickerName);
		writer.writeInput(dateStr);
		date.click();
		datePickerDay.setDate(dateStr);
	}
	
  public String getSelectedDate() {
    return this.date.getAttribute("value");
  }
}