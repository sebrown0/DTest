/**
 * 
 */
package object_models.panels;

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
		WebElement btn = controlBar.findElement(By.cssSelector("div[class='jsPanel-btn jsPanel-btn-close']"));		
		btn.click();
	}
}
