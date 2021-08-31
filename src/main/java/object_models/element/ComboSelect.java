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

/**
 * @author Steve Brown
 *
 */
public class ComboSelect implements InputWriter {
	private static final String TYPE = "combo";
	private String id;
	private String placeHolder;
	private WebElement element;
	private String myIdentifier;
	private WebDriver driver;
		
	public ComboSelect(WebDriver driver, WebElement element, String identifier) {
		this.driver = driver;
		this.element = element;
		this.myIdentifier = identifier;
	}
	public ComboSelect(String id, String placeHolder) {
		this.id = id;
		this.placeHolder = placeHolder;
	}	
	
	@Override
	public void writeInput(String txt) {		
		try {
			if(txt != null) {
				element.click();
				WebElement e = driver.findElement(By.className("select2-search__field"));
				e.sendKeys(txt);
				e.sendKeys(Keys.ENTER);
			}	
		}catch (NoSuchElementException e) {
			Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
			logger.error("Could not write to [" + this.toString() + "]");
		}
	}
	
	@Override
	public String toString() {		
		return "Combo [id=" + id + ", placeHolder=" + placeHolder + "]";
		
	}
	
	public String getType() {
		return TYPE;
	}
	public String getId() {
		return id;
	}
	public String getPlaceHolder() {
		return placeHolder;
	}
	public String getMyIdentifier() {
		return myIdentifier;
	}
}
