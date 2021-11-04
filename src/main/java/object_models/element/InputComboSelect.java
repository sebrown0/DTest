/**
 * 
 */
package object_models.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class InputComboSelect extends InputControl {
	private String comboId;
	
	public InputComboSelect(WebDriver driver, WebElement element, String myIdentifier) {
		super(driver, element, myIdentifier);
		
		comboId = element.findElement(By.className("select2-selection__rendered")).getAttribute("id"); 
	}

	@Override
	public void writeInput(String txt) {		
		try {
			if(txt != null) {				
				clickCombo();
				enterTextIntoCombo(txt);				
			}	
		}catch (NoSuchElementException e) {
			Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
			logger.error("Could not write to [" + myIdentifier + "]");
		}
	}
	
	private void clickCombo() {
		WebElement combo = driver.findElement(By.cssSelector("span[id='" + comboId + "']"));
		combo.click();
	}
	
	private void enterTextIntoCombo(String txt) {
		WebElement e = driver.findElement(By.className("select2-search__field"));
		e.sendKeys(txt);
		e.sendKeys(Keys.ENTER);
	}
}
