/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class TextOut implements Control, DisplayedText {
	private WebElement text;

	public TextOut(WebDriver driver, By findBy) {
		setTextOut(driver, findBy);		
	}
	
	private void setTextOut(WebDriver driver, By findBy) {
		try {
			this.text = driver.findElement(findBy);	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not find [TextOut] using [" + findBy + "]");
		}
	}
	
	public String getTextByValue() {
		try {
			return text.getAttribute("value");
		} catch (Exception e) {
			LogManager.getLogger().error("Could not get text by value");
		}
		return "";
	}	
	
	@Override //DisplayedText
	public String getText() {
		try {
			return text.getAttribute("innerHTML");
		} catch (Exception e) {
			LogManager.getLogger().error("Could not get text by value");
		}
		return "";
	}

	@Override
	public boolean isAvailable() {
		LogManager.getLogger().error("NOT IMPLEMENTED");		
		return false;
	}	
}
