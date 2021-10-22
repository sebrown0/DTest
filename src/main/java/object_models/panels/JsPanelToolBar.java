package object_models.panels;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextSetter;

public class JsPanelToolBar implements ContextSetter {
//	private WebDriver driver;
	private WebElement toolBar;	
	private WebDriverWait wait;
	
	public JsPanelToolBar(WebDriver driver, WebElement headerBar) {
//		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		setToolBar(headerBar);		
	}
	
	private void setToolBar(WebElement headerBar) {
		toolBar = headerBar.findElement(By.cssSelector("div[class='jsPanel-hdr-toolbar active']"));
	}
	
	public void switchToPanel(String panelId) {
		showDropDownMenu();
		if(loadPanel(panelId)){
			switchToPanelsContext();
		}
	}
	
	public void showDropDownMenu() {
		try {
			toolBar.findElement(By.id("dropdownMenuLink")).click();	
		} catch (NoSuchElementException e) {
			LogManager.getLogger().error("Could not find the dropdown");
		}
	}
	
	private boolean loadPanel(String panelId) {
		try {
			LogManager.getLogger().debug("Switching to panel [" + panelId + "]");
			WebElement panel = wait.until(
				ExpectedConditions.elementToBeClickable(
						toolBar.findElement(By.cssSelector("span[data-panel='" + panelId + "']"))));			
			panel.click();
			return true;
		} catch (NoSuchElementException e) {
			LogManager.getLogger().error("Could not find the panel [" + panelId + "]");
			return false;
		}
	}
	
	private void switchToPanelsContext() {
		
	}

	@Override
	public void setContext() {
		// TODO Auto-generated method stub
		
	}
}
