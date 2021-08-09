/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.HrefJavascript;
import utils.HrefLink;

/**
 * @author Steve Brown
 *
 */
public class QuickLink {
	private WebDriver driver;	
	private By locator; 
	
	public QuickLink(WebDriver driver, By locator) {		
		this.driver = driver;
		this.locator = locator;
	}
	
	public void clickMe() {		
		String href = driver.findElement(locator).getAttribute("href");
		HrefLink hrefLink = new HrefJavascript(href, driver);
		hrefLink.followLink();
	}

}
