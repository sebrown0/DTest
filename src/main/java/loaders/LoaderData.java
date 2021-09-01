/**
 * 
 */
package loaders;

import java.util.List;

import org.openqa.selenium.WebElement;

import object_models.element.InputWriter;

/**
 * @author Steve Brown
 *
 */
public interface LoaderData {
	List<WebElement> getElements();
	InputWriter getControlObject(WebElement e);	
	String getIdentifier();
	String getLoaderName();
}
