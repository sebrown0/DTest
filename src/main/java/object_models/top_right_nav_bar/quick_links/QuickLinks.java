/**
 * 
 */
package object_models.top_right_nav_bar.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class QuickLinks {
	private WebDriver driver;
	private By byCssQuickLinksLocator = By.cssSelector("a[class='nav-link nav-pill']");

	public QuickLinks(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickQuickLinks() {
		WebElement ql = driver.findElement(byCssQuickLinksLocator);
		ql.click();
	}
	
}
