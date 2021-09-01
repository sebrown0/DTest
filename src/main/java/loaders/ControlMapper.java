/**
 * 
 */
package loaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.InputWriter;

/**
 * @author Steve Brown
 *
 */
public class ControlMapper {
	private LoaderData loaderData;
	private Logger logger = LogManager.getLogger();
	private Map<String, InputWriter> controls = new HashMap<>();
	
	public ControlMapper(WebDriver driver, LoaderData loaderData) {
		this.loaderData = loaderData;
	}

	public ControlMapper loadControls() {
		logger.debug("Attempting to map controls for [" + loaderData.getLoaderName() + "]");
		List<WebElement> elements = loaderData.getElements();		
		for (WebElement e : elements) {
			InputWriter control = loaderData.getControlObject(e);
			controls.put(loaderData.getIdentifier(), control);			
		}
		return this;
	}
			
	public Map<String, InputWriter> getControls(){
		return controls;
	}
}
