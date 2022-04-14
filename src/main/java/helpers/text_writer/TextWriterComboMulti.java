/**
 * 
 */
package helpers.text_writer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import library.dakar_hr.pages.homepage.CoreData;


/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Write text to a multi-entry combo, where
 * the input element is part of the container
 * element. Usually, the same element that's
 * the top level element for the combo.
 *
 */
public class TextWriterComboMulti implements TextWriter {
	private WebElement container;
	private CoreData coreData;
	
	public TextWriterComboMulti(CoreData coreData, WebElement container) {
		this.coreData = coreData;
		this.container = container;
	}

	@Override
	public void writeText(String txt) {
		try {
			WebElement ip = container.findElement(By.cssSelector("input[class='select2-search__field']"));
			ip.sendKeys(txt);
			ip.sendKeys(Keys.ENTER);	
		} catch (Exception e) {
			coreData.getLogger().error("Unable to write text to combo [" + e.getMessage() + "]");
		}		
	}

}
