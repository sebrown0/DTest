/**
 * 
 */
package controls;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class ComboWriteAndSelect extends ComboSelectFromList {
		
	public ComboWriteAndSelect(WebDriver driver, By findBy, By resultsBy) {
		super(driver, findBy, resultsBy);
	}
		
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	public void writeText(String txt) {
		By dropdownLocator = By.cssSelector("body > span > span > span.select2-search.select2-search--dropdown > input");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		try {			
			wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));
			
			WebElement dropdown = driver.findElement(dropdownLocator);
			dropdown.clear();
			dropdown.sendKeys(txt);
			dropdown.sendKeys(Keys.ENTER);	
		} catch (Exception e) {
			super.getLogger().error("Could not send text to drop down");
		}		
	}
	
}
