/**
 * 
 */
package object_models.strategies.title;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * Get the element's innerHTML attribute.
 */
public class TitleInInnerHTML implements TitleStrategy{

	@Override
	public String getTitle(WebDriver driver, By titleLocator) {		
		WebElement we = driver.findElement(titleLocator); 
		return we.getAttribute("innerHTML");
	}

}
