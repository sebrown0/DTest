/**
 * 
 */
package object_models.panels;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class JsPanelControlBar {
	private WebElement controlBar;
		
	public JsPanelControlBar(WebElement headerBar) {
		setControlBar(headerBar);
	}
	
	private void setControlBar(WebElement headerBar) {		
		controlBar = headerBar.findElement(By.cssSelector("div[class='jsPanel-controlbar']"));
	}
	
	public void clickClose() {
		try {
			WebElement btn = controlBar.findElement(By.cssSelector("div[class='jsPanel-btn jsPanel-btn-close']"));		
			btn.click();	
		} catch (Exception e) { 	
			LogManager.getLogger().debug("Could not close panel. Either in the wrong context/state or the panel is not open");
		}		
	}
	
}
