/**
 * 
 */
package object_models.date_picker;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.stream.LongStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import object_models.helpers.AppDates;

/**
 * @author Steve Brown
 *
 * Select a date (day) in a date picker.
 * Get the current date of the date picker and
 * move either backwards or forwards until the 
 * correct month and year are found.
 * 
 * Then click the given day.
 */
public class DatePickerDay {
  public static final String DATE_FORMAT = "dd MMMM yyyy";
  
  private static final String DAY_FIRST = "01";
  private static final String SPACE = " ";  
  private static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .appendPattern(DATE_FORMAT)
      .toFormatter(Locale.ENGLISH);
  
	@FindBy(how = How.CSS, using = "th[class='prev']")
  private WebElement prev;

  @FindBy(how = How.CSS, using = "th[class='next']")
  private WebElement next;
  
  @FindBy(css = "th.datepicker-switch")
  private WebElement curDate;

  @FindBy(css = "td.day")
  private List<WebElement> dates;
  
  public DatePickerDay(final WebDriver driver){
  	PageFactory.initElements(driver, this);
  }
  
  //Pass the date as an App date string, i.e. dd/mm/yyyy.
  public void setDate(String date) {
  	String pickerDate = AppDates.getAsDatePicker(date);
  	long diff = this.getDateDifferenceInMonths(pickerDate);
    int day = this.getDay(pickerDate);
    
    WebElement arrow = (diff >= 0 ? next : prev);
    diff = Math.abs(diff);
    
    //click the arrows
    LongStream.range(0, diff).forEach(i -> arrow.click());

    //select the date
    dates.stream()
            .filter(ele -> Integer.parseInt(ele.getText()) == day)
            .findFirst()
            .ifPresent(ele -> ele.click());    
  }
		
	private int getDay(String date) {
		LocalDate dpToDate = LocalDate.parse(date, DTF);		
	  return dpToDate.getDayOfMonth();
	}
		
	private long getDateDifferenceInMonths(String date) {
    LocalDate dpCurDate = LocalDate.parse(DAY_FIRST + SPACE + this.getCurrentMonthFromDatePicker(), DTF);
    LocalDate dpToDate = LocalDate.parse(date, DTF);
    return YearMonth.from(dpCurDate).until(dpToDate, ChronoUnit.MONTHS);
	}
		
	private String getCurrentMonthFromDatePicker() {
    return this.curDate.getText();
	}
}
