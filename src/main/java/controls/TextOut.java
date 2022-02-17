/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TextOut implements Control, DisplayedText {
	private WebElement text;
	private Logger logger = LogManager.getLogger(this.getClass());
	
	public TextOut(WebElement text) {
		this.text = text;
	}
	
	public TextOut(WebDriver driver, By findBy) {
		setTextOut(driver, findBy);		
	}	

	private void setTextOut(WebDriver driver, By findBy) {
		try {
			this.text = driver.findElement(findBy);	
		} catch (Exception e) {
			logger.error("Could not find [TextOut] using [" + findBy + "]");
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

	@Override
	public boolean isAvailable() {
		logger.error("NOT IMPLEMENTED");		
		return false;
	}	
}
