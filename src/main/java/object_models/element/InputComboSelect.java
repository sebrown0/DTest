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
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Steve Brown
 *
 */
public class InputComboSelect extends InputControl {	

	public InputComboSelect(WebDriver driver, WebElement element, String myIdentifier) {
		super(driver, element, myIdentifier);
	}

	@Override
	public void writeInput(String txt) {		
		try {
			if(txt != null) {
//				wait.until(ExpectedConditions.elementToBeClickable(element));
//				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				WebElement e = driver.findElement(By.className("select2-search__field"));
				e.sendKeys(txt);
				e.sendKeys(Keys.ENTER);
			}	
		}catch (NoSuchElementException e) {
			Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
			logger.error("Could not write to [" + myIdentifier + "]");
		}
	}
}
