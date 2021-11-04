/**
 * 
 */
package object_models.panels;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
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
	private WebDriver driver;
	
	public JsPanelHeaderBar(WebDriver driver, WebElement container) {
		this.driver = driver;		
		this.initialise(container);		
	}
	
	private void initialise(WebElement container) {
		try {
			setHeaderBar(container);
			setControlBar();
			setToolBar(driver);	
		} catch (Exception e) {
			LogManager.getLogger().error("Error initialising JsPanelHeaderBar [" + e.getMessage() + "]");
		}
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

	@Override
	public void closeForm() {
		driver.switchTo().defaultContent();
		controlBar.clickClose();
	}
	
	public JsPanelControlBar getControlBar() {
		return controlBar;
	}
	public JsPanelToolBar getToolBar() {
		return toolBar;
	}
	
}
