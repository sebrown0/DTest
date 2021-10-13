/**
 * 
 */
package object_models.top_right_nav_bar.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	private String originalName;
	private static final By NAV_BAR_LOCATOR = By.cssSelector("body > form > header > ul.nav.navbar-nav.ml-auto");
	
	public NavBarElement(WebDriver driver, String originalName, ContextManager contextManager) {
		this.driver = driver;			
		this.originalName = originalName;
		this.contextManager = contextManager;
		navBar = driver.findElement(NAV_BAR_LOCATOR);		
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	/*
	 * Click the element and return its child.
	 */	
	public abstract Closable clickElement(); 
	
}
