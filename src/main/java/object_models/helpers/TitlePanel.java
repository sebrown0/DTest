/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class TitlePanel extends Title{	
	public TitlePanel(String expectedTitle, WebDriver driver) {
		super(expectedTitle, driver, By.cssSelector("span[class='jsPanel-title']"));
	} 
}
