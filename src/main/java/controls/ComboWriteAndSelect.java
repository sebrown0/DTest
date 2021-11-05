/**
 * 
 */
package controls;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;

/**
 * @author Steve Brown
 *
 */
public class ComboWriteAndSelect extends Combo {
		
	public ComboWriteAndSelect(WebDriver driver, By findBy) {
		super(driver, findBy);
	}
		
	public ComboWriteAndSelect(WebDriver driver, WebElement combo) {
		super(driver, combo);	
	}

	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	public void writeText(String txt) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(combo));
		combo.clear();
		combo.sendKeys(txt);
	}		
}
