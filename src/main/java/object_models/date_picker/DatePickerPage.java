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
 */
public class DatePickerPage extends DatePicker{
//	private WebDriver driver;
//	private Map<String, WebElement> dateSelectorsMap = new HashMap<>();
//	private Logger logger = LogManager.getLogger();
//	private WebElement date;
		
	public DatePickerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//	@FindBy(css = "input[title='Date selector']")
//  private List<WebElement> dateSelectorsList;
	
//	public DatePickerPage(WebDriver driver) {
//		super(driver);
////		this.driver = driver;
////		PageFactory.initElements(driver, this);
//		mapSelectors();
//	}
	
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
	
//	public DatePickerDay getDatePicker(String pickerName) {
//		date = dateSelectorsMap.get(pickerName);
//		date.click();
//  	return new DatePickerDay(driver);
//	}
//		
//	public void writeDate(String pickerName, InputWriter writer, String dateStr) {
//		DatePickerDay datePickerDay = new DatePickerDay(driver);
//		date = dateSelectorsMap.get(pickerName);
//		writer.writeInput(dateStr);
//		date.click();
//		datePickerDay.setDate(dateStr);
//	}
//	
//  public String getSelectedDate() {
//    return this.date.getAttribute("value");
//  }
}
