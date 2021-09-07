/**
 * 
 */
package object_models.helpers.closers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 *
 */
public class CloserModalForm implements Closable{
	private WebDriver driver;
	
	public CloserModalForm(WebDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public void close() {
		By byLocator = By.cssSelector("body > div.modal.show > div > div > div.modal-header > button");
		WebElement we = driver.findElement(byLocator);
		we.click();
	}

}
