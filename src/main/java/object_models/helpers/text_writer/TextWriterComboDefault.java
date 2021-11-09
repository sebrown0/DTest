/**
 * 
 */
package object_models.helpers.text_writer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 * Write text to a combo that has been 'OPENED'.
 * And therefore, found in the DOM not from the 
 * combo's top level container.
 * 
 * For this to work the drop-down element of the 
 * combo must be open, i.e. clicked.
 */
public class TextWriterComboDefault implements TextWriter {
	private WebDriver driver;
	
	public TextWriterComboDefault(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void writeText(String txt) {
		WebElement cont = driver.findElement(By.cssSelector("span[class='select2-container select2-container--default select2-container--open']"));
		WebElement ip = cont.findElement(By.cssSelector("input[class='select2-search__field']"));
		ip.sendKeys(txt);	
		ip.sendKeys(Keys.ENTER);
	}

}
