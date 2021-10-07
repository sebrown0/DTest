/**
 * 
 */
package object_models.element;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.Control;

/**
 * @author Steve Brown
 *
 */
public class TextOut implements Control {
	private WebElement text;
	
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
			LogManager.getLogger().error("Could not find [TextOut] using [" + findBy + "]");
		}
	}
	
	public String getTextByValue() {
		return text.getAttribute("value");
	}	
}
