/**
 * 
 */
package object_models.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.ChildElement;

/**
 * @author SteveBrown
 * <p> Represents an element on the nav bar.
 */
public abstract class NavBarElement {
	protected WebDriver driver;	
	private By locator;	
	
	public NavBarElement(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;		
	}

//	public void clickElement() {
//		System.out.println("NavBarElement.clickElement is depreciated!!!!!!!!");
////		String jsString =	driver.findElement(locator).getAttribute("onclick");
////		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
////		if (jsString != null) {
////			jsString.replaceAll("javascript:", "");
////			jsExecutor.executeScript(jsString);
////		}
//	}

	public By getLocator() {
		return locator;
	}
	
	public abstract ChildElement clickElement(); 
	public abstract String getOriginalName();	
}
