/**
 * 
 */
package object_models.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 *
 */
public abstract class InputControl implements InputWriter{
	protected WebDriver driver;
	protected WebElement element;
	protected String myIdentifier;
	
	public InputControl(WebDriver driver, WebElement element, String myIdentifier) {
		this.driver = driver;
		this.element = element;
		this.myIdentifier = myIdentifier;		
	}
	
	public String getMyIdentifier() {
		return myIdentifier;
	}
}
