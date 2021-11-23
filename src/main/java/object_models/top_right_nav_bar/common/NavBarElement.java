/**
 * 
 */
package object_models.top_right_nav_bar.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
import object_models.helpers.Closable;

/**
 * @author SteveBrown
 * @since 1.0
 * @version 1.1 
 * 
 * Represents an element on the nav bar.
 * 
 */
public abstract class NavBarElement {
	private WebElement navBar;
	private String originalName;
	
	protected WebDriver driver;	
	protected ContextManager contextManager;
	protected WebDriverWait wait;
		
	private static final By NAV_BAR_LOCATOR = By.cssSelector("ul[class='nav navbar-nav ml-auto'");
	
	public NavBarElement(WebDriver driver, String originalName, ContextManager contextManager) {
		this.driver = driver;			
		this.originalName = originalName;
		this.contextManager = contextManager;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		navBar = driver.findElement(NAV_BAR_LOCATOR);		
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	protected WebElement getNavBar() {
		driver.switchTo().defaultContent();
		WebElement frm = driver.findElement(By.cssSelector("body > form"));		
		navBar = frm.findElement(NAV_BAR_LOCATOR);
		return navBar;
	}

	/*
	 * Click the element and return its child.
	 */	
	public abstract Closable clickElement(); 
	
}
