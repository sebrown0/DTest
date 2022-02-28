/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.getters.ElementGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * This was added for InitialisePayroll.
 * Where there are select boxes that cannot select,
 * or don't have a list to select from.
 */
public class TextSelect implements Control, DisplayedText {
	private WebElement text;
	private WebDriver driver;
	private By locator;
		
	public TextSelect(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
		
		setTextOut();		
	}
	
	private void setTextOut() {
		try {
			this.text = driver.findElement(locator);	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not find [TextOut] using [" + locator + "]");
		}
	}
		
	public String getText() {
		try {
			WebElement option = text.findElement(By.cssSelector("option[value*='java']"));			
			return option.getText();
		} catch (Exception e) {
			LogManager.getLogger().error("Could not get text by value");
		}
		return "";
	}	
	
	@Override //Control
	public boolean isAvailable() {
		text = new ElementGetter(driver).getElementIfClickable(this);
		return (text != null) ? true : false;
	}	
	
	@Override //Control
	public By getLocator() {
		return locator;
	}

	@Override //Control
	public WebDriver getDriver() {
		return driver;
	}

	@Override //Control
	public WebElement getElement() {
		return text;
	}
	
}
