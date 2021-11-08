/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;

/**
 * @author Steve Brown
 *
 */
public abstract class Combo implements Control {
	protected boolean isOpen = false;	
	protected WebElement combo;
	protected WebDriver driver;
	protected By comboLocator;
	
	public Combo(WebDriver driver, WebElement combo) {
		this.combo = combo;
		this.driver = driver;
	}
	
	public Combo(WebDriver driver, By findBy) {
		this.driver = driver;
		this.combo = driver.findElement(findBy);
		this.comboLocator = findBy;
	}
	
	public void click() {
		isOpen = !isOpen;
		combo.click();
	}
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	protected Logger getLogger() {
		return LogManager.getLogger();
	}
}
