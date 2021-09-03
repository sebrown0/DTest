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
public class TextInOut {
	private WebElement text;
	
	public TextInOut(WebDriver driver, By findBy) {
		this.text = driver.findElement(findBy);
	}
	
	public String getTextByValue() {
		return text.getAttribute("value");
	}
	
	public void writeText(String txt) {
		text.clear();
		text.sendKeys(txt);
	}
}
