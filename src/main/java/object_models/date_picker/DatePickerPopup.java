/**
 * 
 */
package object_models.date_picker;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.dk_grid.Popup;

/**
 * @author Steve Brown
 *
 * Date picker that is a popup from another element, i.e. grid cell.
 */
public class DatePickerPopup extends DatePicker implements Popup {
	
	public DatePickerPopup(WebDriver driver) {
		super(driver);	
	}
		
	@Override
	protected void mapSelectors() {
		Optional<WebElement> picker = Optional.ofNullable(driver.findElement(By.cssSelector("div[class='datepicker datepicker-inline']")));
		picker.ifPresentOrElse(p -> {
				dateSelectorsMap.put("inline", p);
				logger.debug("Found [inline] date picker");
			},				
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Could not find [inline] date picker"); 	
					}
		});		
	}	
	
}