/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class QuickLinks {
	private WebDriver driver;
	private QuickLink qlPayroll;
	private By byXpathQuickLink = By.xpath("html/body/form/header/ul[4]/li[11]/a");
	
	public QuickLinks(WebDriver driver) {
		this.driver = driver;
		initAllLinks();
	}
	
	private void initAllLinks() {
		qlPayroll = new QuickLinkPayroll(driver);
	}
	
	public void clickQuickLinks() {
		WebElement ql = driver.findElement(byXpathQuickLink);
		ql.click();
	}
	
	public void clickPayroll() {
		qlPayroll.clickMe();
		implicitlyWaitForThePageToLoad();
	}
	
	private void implicitlyWaitForThePageToLoad() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));	
	}
}
