/**
 * 
 */
package object_models.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class TextInput implements InputWriter {
	private static final String TYPE = "text";
	private String id;
	private String placeHolder;
	private By byLocator;
	private WebElement element;
	
	private String myIdentifier;
	
	public TextInput(WebElement element, String identifier) {		
		this.element = element;
		this.myIdentifier = identifier;
	}
	public TextInput(String id, String placeHolder) {
		this.id = id;
		this.placeHolder = placeHolder;
	}
	
	@Override
	public void writeInput(String txt) {		
		try {
//			WebElement ip = driver.findElement(byLocator);
//			ip.sendKeys(txt);	
			if(txt != null) {
				element.sendKeys(txt);
			}
			
		}catch (NoSuchElementException e) {
			Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
			logger.error("Could not write to [" + this.toString() + "]");
		}
	}
	
	@Override
	public String toString() {
		if(byLocator == null) {
			return "TextInput [id=" + id + ", placeHolder=" + placeHolder + ", byLocator=null";
		}else {
			return "TextInput [id=" + id + ", placeHolder=" + placeHolder + ", byLocator=" + byLocator.toString() + "]";
		}
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
