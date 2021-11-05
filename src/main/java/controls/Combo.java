/**
 * 
 */
package controls;

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
	protected WebElement combo;
	protected WebDriver driver;
	
	public Combo(WebDriver driver, WebElement combo) {
		this.combo = combo;
		this.driver = driver;
	}
	
	public Combo(WebDriver driver, By findBy) {
		this.driver = driver;
		this.combo = driver.findElement(findBy);
	}
	
	public void click() {
		combo.click();
	}
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
}
