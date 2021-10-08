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
 * @author Steve Brown
 *
 */
public class JsPanelId {
	private static Optional<String> panelId = Optional.empty();
	
	public static Optional<String> getPanelIdForTitle(WebDriver driver, String expectedTitle) {		
		try {
			List<WebElement> panelIds = driver.findElements(By.cssSelector("div[id^=jsPanel"));//.getAttribute("id");
			panelIds.forEach(id -> {
				String title = id.findElement(By.cssSelector("span[class='jsPanel-title']")).getText();
				if(title.equalsIgnoreCase(expectedTitle)) {
					panelId = Optional.of(id.getAttribute("id"));
//					System.out.println("Found id for ->" + expectedTitle + "=" + title + "->" + id.getAttribute("id"));	
				}				
			});			
		} catch (Exception e) {
			LogManager.getLogger().error("Could not set panel id for [" + expectedTitle + "]");
		}
		return panelId;
	}
}
