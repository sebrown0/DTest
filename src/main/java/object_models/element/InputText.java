/**
 * 
 */
package object_models.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class InputText extends InputControl {

	public InputText(WebDriver driver, WebElement element, String myIdentifier) {
		super(driver, element, myIdentifier);
	}

	@Override
	public void writeInput(String txt) {		
		try {
			if(txt != null) {	element.sendKeys(txt); }			
		}catch (NoSuchElementException e) {
			Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
			logger.error("Could not write to [" + myIdentifier + "]");
		}
	}	
	
}
