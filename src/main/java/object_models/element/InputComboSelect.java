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

import object_models.helpers.ElementClicker;

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
		ElementClicker.tryClickingElement(driver, By.cssSelector("span[id='" + comboId + "']"));
	}
	
	private void enterTextIntoCombo(String txt) {		
		WebElement combo = driver.findElement(afterPopListIsVisible());
		combo.sendKeys(txt);
		combo.sendKeys(Keys.ENTER);
	}	
	private By afterPopListIsVisible() {
		By listLocator = By.className("select2-search__field");
		wait.until(ExpectedConditions.visibilityOfElementLocated(listLocator));
		return listLocator;
	}
}
