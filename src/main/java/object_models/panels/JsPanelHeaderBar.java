/**
 * 
 */
package object_models.panels;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.Header;

/**
 * @author Steve Brown
 *
 */
public class JsPanelHeaderBar implements Header{	
	private WebElement headerBar;
	private JsPanelControlBar controlBar;
	private JsPanelToolBar toolBar;
	
	public JsPanelHeaderBar(WebDriver driver, WebElement container) {		
		setHeaderBar(container);
		setControlBar();
		setToolBar(driver);		
	}
	
	private void setHeaderBar(WebElement container) {
		headerBar = container.findElement(By.cssSelector("div[class='jsPanel-headerbar']"));
	}
	private void setControlBar() {
		controlBar = new JsPanelControlBar(headerBar);
	}
	private void setToolBar(WebDriver driver) {
		toolBar = new JsPanelToolBar(driver, headerBar);
	}
	
	@Override
	public Optional<String> getTitle() {
		return Optional.ofNullable(headerBar.findElement(By.cssSelector("span[class='jsPanel-title']")).getText());
	}

	public JsPanelControlBar getControlBar() {
		return controlBar;
	}
	public JsPanelToolBar getToolBar() {
		return toolBar;
	}
	
}
