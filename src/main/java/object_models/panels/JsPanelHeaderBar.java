/**
 * 
 */
package object_models.panels;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class JsPanelHeaderBar {	
	private WebElement headerBar;
	private JsPanelControlBar controlBar;
	
	public JsPanelHeaderBar(WebElement container) {		
		setHeaderBar(container);
		setControlBar();
	}
	
	private void setHeaderBar(WebElement container) {
		headerBar = container.findElement(By.cssSelector("div[class='jsPanel-headerbar']"));
	}
	
	private void setControlBar() {
		controlBar = new JsPanelControlBar(headerBar);
	}
	
	public Optional<String> getTitle() {
		return Optional.ofNullable(headerBar.findElement(By.cssSelector("span[class='jsPanel-title']")).getText());
	}

	public JsPanelControlBar getControlBar() {
		return controlBar;
	}
	
}
