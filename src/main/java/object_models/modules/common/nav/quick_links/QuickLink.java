/**
 * 
 */
package object_models.modules.common.nav.quick_links;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.HrefJavascript;
import utils.HrefLink;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add try catch block.
 * @since 1.0
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
		try {
			WebElement grid = driver.findElement(QL_GRID_LOCATOR); 		
			//Get link from unique grid element. Then move up a level to the link.
			WebElement link = grid.findElement(linkLocator).findElement(By.xpath("./..")); 
			String href = link.getAttribute("href");
			HrefLink hrefLink = new HrefJavascript(href, driver);
			hrefLink.followLink();			
		} catch (Exception e) {
			LogManager.getLogger().error("Error clicking QuickLink [" + e.getMessage() + "]");
		}
		
	}
}
