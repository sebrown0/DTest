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
import controls.getters.TextGetter;
import controls.reset.ReloadContainer;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TextOut implements Control, DisplayedText, ReloadContainer {
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
	
	@Override //DisplayedText
	public String getText() {
		return new TextGetter(text, this).getText();
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

	@Override //ReloadContainer
	public WebElement reloadContainer() {
		setTextOut();
		return text;
	}

}
