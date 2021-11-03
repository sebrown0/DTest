/**
 * 
 */
package controls;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.InputText;
import object_models.element.InputWriter;

/**
 * @author SteveBrown
 *
 */
public class ControlText implements MapControl{
	private WebDriver driver;
	private By byLocator;	
	private String attributeKey;
	
	public ControlText(WebDriver driver, By byLocator, String attributeKey) {
		this.driver = driver;
		this.byLocator = byLocator;
		this.attributeKey = attributeKey;
	}

	@Override
	public void addToPageMap(PageMap pageMap) {
		InputWriter input = null;
		String key = null;
		List<WebElement> elements = driver.findElements(byLocator);
		for (WebElement el : elements) {
			key = el.getAttribute(attributeKey);
			input = new InputText(driver, el, key);
			pageMap.addTextBox(key, input);
		}				 	
	}
}
