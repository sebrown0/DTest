/**
 * 
 */
package object_models.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.Control;
import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;

/**
 * @author Steve Brown
 *
 */
public class ComboSelect implements Control {
	private WebElement combo;
	
	public ComboSelect(WebElement element) {
		this.combo = element;
	}
	
	public ComboSelect(WebDriver driver, By findBy) {
		this.combo = driver.findElement(findBy);
	}
	
	public void click() {
		combo.click();
	}
	
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	public void writeText(String txt) {
		combo.clear();
		combo.sendKeys(txt);
	}
}
