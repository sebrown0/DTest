/**
 * 
 */
package controls.reset;

import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Attempt to reload the element's 
 * container [WebElement].
 * 
 * Can be used by another object to reload 
 * the control/container passed to it.
 */
public interface ReloadContainer {
	WebElement reloadContainer();
}
