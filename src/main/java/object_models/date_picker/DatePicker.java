/**
 * 
 */
package object_models.date_picker;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.InputWriter;

/**
 * @author Steve Brown
 *
 */
public abstract class DatePicker {
	protected WebDriver driver;
	protected Map<String, WebElement> dateSelectorsMap = new HashMap<>();	
	protected Logger logger = LogManager.getLogger();
	
	private WebElement date;
				
	public DatePicker(WebDriver driver) {
		this.driver = driver;
		mapSelectors();
	}

	protected abstract void mapSelectors();
	
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
	
}
