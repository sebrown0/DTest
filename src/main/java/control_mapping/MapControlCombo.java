/**
 * 
 */
package control_mapping;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.element.InputComboSelect;
import library.element.InputWriter;

/**
 * @author Steve Brown
 *
 */
public class MapControlCombo implements MapControl{
	private WebDriver driver;
	private By byLocator;	
	private String attributeKey;
	
	public MapControlCombo(WebDriver driver, By byLocator, String attributeKey) {
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
			input = new InputComboSelect(driver, el, key);
			pageMap.addComboBox(key, input);
		}						
	}

}
