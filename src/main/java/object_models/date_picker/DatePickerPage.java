/**
 * 
 */
package object_models.date_picker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



/**
 * @author Steve Brown
 *
 */
public class DatePickerPage {
	private WebDriver driver;
	
	@FindBy(how = How.CSS, using = "input[class='form-control wiz-datepicker']")
	private WebElement dateField;
	
	public DatePickerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	public DatePickerDay getDatePicker() {
		dateField.click();
  	return new DatePickerDay(driver);
	}
	
  public String getSelectedDate() {
    return this.dateField.getAttribute("value");
  }
}
