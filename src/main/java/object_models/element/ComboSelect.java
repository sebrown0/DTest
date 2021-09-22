/**
 * 
 */
package object_models.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class ComboSelect {
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
	
	public String getTextByValue() {
		return combo.getAttribute("value").trim();
	}
	
	public String getText() {
		return combo.getText().trim();
	}
	
	public void writeText(String txt) {
		combo.clear();
		combo.sendKeys(txt);
	}
}
