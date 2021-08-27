/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.HrefJavascript;
import utils.HrefLink;

/**
 * @author Steve Brown
 *
 * An element on the QuickLink grid.
 * The instance variable linkLocator is supplied by the sub-class and finishes the path to the 'link'.
 */
public class QuickLink {
	protected WebDriver driver;
	
	private By linkLocator;
	private static final By QL_GRID_LOCATOR = By.cssSelector("div[class='dropdown-menu dropdown-menu-right dropdown-menu-ql-gird']");
	
	public QuickLink(WebDriver driver, By locator) {		
		this.driver = driver;
		this.linkLocator = locator;
	}
	
	public void clickMe() {		
		WebElement grid = driver.findElement(QL_GRID_LOCATOR); 		
		//Get link from unique grid element. Then move up a level to the link.
		WebElement link = grid.findElement(linkLocator).findElement(By.xpath("./..")); 
		String href = link.getAttribute("href");
		HrefLink hrefLink = new HrefJavascript(href, driver);
		hrefLink.followLink();		
	}
}
