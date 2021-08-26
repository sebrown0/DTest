/**
 * 
 */
package object_models.strategies.title;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public interface TitleStrategy {
	String getTitle(WebDriver driver, By titleLocator);
}
