/**
 * 
 */
package object_models.element;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SteveBrown
 *
 */
public abstract class InputControl implements InputWriter{
	protected WebDriver driver;
	protected WebElement element;
	protected WebDriverWait wait;
	protected String myIdentifier;
	
	public InputControl(WebDriver driver, WebElement element, String myIdentifier) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		this.element = element;
		this.myIdentifier = myIdentifier;		
	}
	
	public String getMyIdentifier() {
		return myIdentifier;
	}
}
