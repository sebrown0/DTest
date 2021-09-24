package object_models.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.date_picker.DatePickerDay;
import object_models.date_picker.DateSetter;

public final class ElementPointInTime {
	private WebDriver driver;
	private WebElement element;
	
	public ElementPointInTime(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
	}

	public DateSetter click() {
		element.click();
		return new DatePickerDay(driver);
	}
}
