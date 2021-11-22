/**
 * 
 */
package object_models.helpers.title;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class TitlePanel extends Title{
	public TitlePanel(String expectedTitle, WebDriver driver, WebElement myContainer) {
		super(expectedTitle, driver, By.cssSelector("span[class='jsPanel-title']"), myContainer);
	} 	
}
