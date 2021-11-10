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
		try {
			combo.click();
			isOpen = !isOpen;
		} catch (Exception e) {
			LogManager.getLogger().debug("Unable to click combo [" + e.getMessage() + "]");
		}				
	}
		
	public void clearAll() {
		combo.findElement(By.cssSelector("span[class='select2-selection__clear']")).click();
	}
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	protected Logger getLogger() {
		return LogManager.getLogger();
	}
}
