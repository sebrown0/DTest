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
	
	public JsPanelHeaderBar(WebElement container) {		
		setHeaderBar(container);
	}
	
	private void setHeaderBar(WebElement container) {
		headerBar = container.findElement(By.cssSelector("div[class='jsPanel-headerbar']"));
	}
	
	public Optional<String> getTitle() {
		return Optional.ofNullable(headerBar.findElement(By.cssSelector("span[class='jsPanel-title']")).getText());
	}
}
