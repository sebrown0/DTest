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
	private WebDriver driver;
	private WebElement toolBar;	
	private WebDriverWait wait;
	
	public JsPanelToolBar(WebDriver driver, WebElement headerBar) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		setToolBar(headerBar);		
	}
	
	private void setToolBar(WebElement headerBar) {
		toolBar = headerBar.findElement(By.cssSelector("div[class='jsPanel-hdr-toolbar active']"));
	}
	
	public void switchToPanelFromPanel(String toPanelId, JsPanel fromPanel) {
		showDropDownMenu();
		if(loadPanel(toPanelId)){
			switchToPanelsContext();
		}
	}
	
	private void showDropDownMenu() {
		driver.switchTo().defaultContent();
		try {
			toolBar.findElement(By.id("dropdownMenuLink")).click();	
		} catch (NoSuchElementException e) {
			LogManager.getLogger().error("Could not find the dropdown");
		}
	}
	
	private boolean loadPanel(String panelId) {
//		try {
			LogManager.getLogger().debug("Switching to panel [" + panelId + "]");
			
//			WebElement panel = wait.until(
//				ExpectedConditions.elementToBeClickable(
//						toolBar.findElement(By.cssSelector("span[data-panel='" + panelId + "']"))));
			
			WebElement panel = toolBar.findElement(By.cssSelector("span[data-panel='" + panelId + "']"));
			System.out.println("->" +  panel.getText()); // TODO - remove or log 	
			panel.click();
			return true;
//		} catch (NoSuchElementException e) {
//			LogManager.getLogger().error("Could not find the panel [" + panelId + "]");
//			System.out.println("loadPanel->" + toolBar.getAttribute("class") ); // TODO - remove or log
//			return false;
//		}
	}
	//<span class="dropdown-item jsPanel-switch-item" data-panel="jsPanel-1">Monthly Payroll Reports</span>
	private void switchToPanelsContext() {
		
	}

	@Override
	public void setContext() {
		// TODO Auto-generated method stub
		
	}
}
