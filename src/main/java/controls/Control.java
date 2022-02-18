/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 *  Add isAvailable().
 * @version 1.2
 *  Add getLocator(), getDriver().
 * @since 1.0
 * * 
 */
public interface Control {	
	boolean isAvailable();
	By getLocator();
	WebDriver getDriver();
	WebElement getElement();
}
