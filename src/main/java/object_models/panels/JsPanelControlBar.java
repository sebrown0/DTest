/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import context_manager.JsPanelContextManager;

/**
 * @author SteveBrown
 *
 */
public class JsPanelControlBar {
	private WebElement controlBar;
	private JsPanelContextManager contextManager;
	
	public JsPanelControlBar(WebElement headerBar, JsPanelContextManager contextManager) {
		this.contextManager = contextManager;
		setControlBar(headerBar);
	}
	
	private void setControlBar(WebElement headerBar) {		
		controlBar = headerBar.findElement(By.cssSelector("div[class='jsPanel-controlbar']"));
	}
	
	public void clickClose() {
		controlBar.findElement(By.cssSelector("div[class='jsPanel-btn jsPanel-btn-close']")).click();;
	}
}
