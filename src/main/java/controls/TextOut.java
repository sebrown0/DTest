/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.getters.ElementGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TextOut implements Control, DisplayedText {
	private WebDriver driver;
	private By locator;
	private WebElement text;
	private Logger logger = LogManager.getLogger(this.getClass());
		
	public TextOut(WebDriver driver, By findBy) {
		this.driver = driver;
		this.locator = findBy;
		
		setTextOut();		
	}	

	private void setTextOut() {
		try {
			this.text = driver.findElement(locator);	
		} catch (Exception e) {
			logger.error("Could not find [TextOut] using [" + locator + "]");
		}
	}
	
	public String getTextByValue() {
		try {
			return text.getAttribute("value");
		} catch (Exception e) {
			logger.error("Could not get text by value");
		}
		return "";
	}	
	
	@Override //DisplayedText
	public String getText() {
		try {
			return text.getAttribute("innerHTML");
		} catch (Exception e) {
			logger.error("Could not get text by value");
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
