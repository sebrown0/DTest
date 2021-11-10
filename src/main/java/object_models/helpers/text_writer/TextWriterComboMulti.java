/**
 * 
 */
package object_models.helpers.text_writer;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 * 
 * Write text to a multi-entry combo, where
 * the input element is part of the container
 * element. Usually, the same element that's
 * the top level element for the combo.
 *
 */
public class TextWriterComboMulti implements TextWriter {
	private WebElement container;
	
	public TextWriterComboMulti(WebElement container) {
		this.container = container;
	}

	@Override
	public void writeText(String txt) {
		try {
			WebElement ip = container.findElement(By.cssSelector("input[class='select2-search__field']"));
			ip.sendKeys(txt);
			ip.sendKeys(Keys.ENTER);	
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to write text to combo [" + e.getMessage() + "]");
		}		
	}

}
