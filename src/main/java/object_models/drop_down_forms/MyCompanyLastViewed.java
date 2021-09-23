/**
 * 
 */
package object_models.drop_down_forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class MyCompanyLastViewed implements ChildElement {
//	private WebElement lastViewed;
	private WebDriver driver;
	private String title;
	
	public MyCompanyLastViewed(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;
	}
	
//	public MyCompanyLastViewed(WebElement lastViewed, String title) {
//		this.lastViewed = lastViewed;
//		this.title = title;
//	}

	public void click() {
		WebElement navBar = driver.findElement(By.cssSelector("header[class='app-header navbar']"));
		navBar.click();
//		WebElement el = driver.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-building')]"));
//		Jquery.goToElement(driver, el);
//		lastViewed.click();
	}
	
	// Elements

	// Tabs
	
	// Getters & Setters
	public String getTitle() {
		return title;
	}

}
