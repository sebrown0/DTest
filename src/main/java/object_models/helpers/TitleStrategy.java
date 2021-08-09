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
public interface TitleStrategy {
	String getTitle(WebDriver driver, By titleLocator);
}
