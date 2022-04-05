/**
 * 
 */
package object_models.panels;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 * Get the JsPanel ID for a given (expected) panel title.
 */
public class JsPanelId {	
	
	public static Optional<String> getPanelIdForTitle(WebDriver driver, String expectedTitle) {
		Optional<String> panelId = Optional.empty();
		try {
			List<WebElement> panelIds = driver.findElements(By.cssSelector("div[id^=jsPanel"));
			for(WebElement id : panelIds) {
				var title = id.findElement(By.cssSelector("span[class='jsPanel-title']")).getText();
				if(title.equalsIgnoreCase(expectedTitle)) {
					panelId = Optional.of(id.getAttribute("id"));
					break;
				}				
			}
		} catch (Exception e) {
			LogManager.getLogger().error("Could not set panel id for [" + expectedTitle + "]");
		}
		return panelId;
	}
	
}
