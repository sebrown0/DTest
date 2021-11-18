/**
 * 
 */
package object_models.element;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Steve Brown
 *
 */
public abstract class InputControl implements InputWriter{
	protected WebDriver driver;
	protected WebElement myContainer;
	protected WebDriverWait wait;
	protected String myIdentifier;
	
	public InputControl(WebDriver driver, WebElement myContainer, String myIdentifier) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.myContainer = myContainer;
		this.myIdentifier = myIdentifier;		
	}
	
	public String getMyIdentifier() {
		return myIdentifier;
	}
}
