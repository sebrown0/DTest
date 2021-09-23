package object_models.drop_down_forms.top_right_nav_bar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.Closable;

/**
 * @author Steve Brown
 *
 */
public class Terminations implements Closable {
	private WebDriver driver;
	private String title;
	
	public Terminations(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;
	}
	
	// Actions
	@Override
	public void close() {
		WebElement navBar = driver.findElement(By.cssSelector("header[class='app-header navbar']"));
		navBar.click();
	}
	
	// Elements

	// Tabs
	
	// Getters & Setters
	public String getTitle() {
		return title;
	}
}