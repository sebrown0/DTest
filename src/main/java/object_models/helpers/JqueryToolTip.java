/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Steve Brown
 *
 */
public class JqueryToolTip {
	
	public static void goToToolTip(WebDriver driver, WebElement toolTip) {
		Actions builder = new Actions(driver);
		builder.moveToElement(toolTip).click().build().perform();					
//    builder.moveToElement(toolTip).build().perform();
	}
	
}
