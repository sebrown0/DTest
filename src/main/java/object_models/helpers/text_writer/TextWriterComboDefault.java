/**
 * 
 */
package object_models.helpers.text_writer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Write text to a combo that has been 'OPENED'.
 * And therefore, found in the DOM not from the 
 * combo's top level container.
 * 
 * For this to work the drop-down element of the 
 * combo must be open, i.e. clicked.
 */
public class TextWriterComboDefault implements TextWriter {
	private CoreData coreData;
	
	public TextWriterComboDefault(CoreData coreData) {
		this.coreData = coreData;
	}

	@Override
	public void writeText(String txt) {
		try {
			WebElement cont = coreData
					.getWebDriver()
					.findElement(By.cssSelector("span[class='select2-container select2-container--default select2-container--open']"));
			WebElement ip = cont.findElement(By.cssSelector("input[class='select2-search__field']"));
			ip.sendKeys(txt);	
			ip.sendKeys(Keys.ENTER);	
		} catch (Exception e) {
			coreData.getLogger().error("Unable to write text to combo [" + e.getMessage() + "]");
		}		
	}

}
