/**
 * 
 */
package object_models.top_right_nav_bar.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
import object_models.helpers.Closable;

/**
 * @author SteveBrown
 * 
 * Represents an element on the nav bar.
 * 
 */
public abstract class NavBarElement {
	protected WebDriver driver;
	protected WebElement navBar;
	protected ContextManager contextManager;
	protected WebDriverWait wait;
	
	private String originalName;
	private static final By NAV_BAR_LOCATOR = By.cssSelector("ul[class='nav navbar-nav ml-auto'");
//	private static final By NAV_BAR_LOCATOR = By.cssSelector("body > form > header > ul.nav.navbar-nav.ml-auto");
	
	public NavBarElement(WebDriver driver, String originalName, ContextManager contextManager) {
		this.driver = driver;			
		this.originalName = originalName;
		this.contextManager = contextManager;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		navBar = driver.findElement(NAV_BAR_LOCATOR);		
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	protected WebElement getNavBar() {
//		driver.switchTo().defaultContent();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(NAV_BAR_LOCATOR)).findElement(NAV_BAR_LOCATOR);
	}
	
	/*
	 * Click the element and return its child.
	 */	
	public abstract Closable clickElement(); 
	
}
