/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public class Title {
	private By locator;
	private String expectedTitle;	
	private TitleStrategy titleStrategy;
	
	public Title(By locator, String expectedTitle, TitleStrategy titleStrategy) {
		this.locator = locator;
		this.expectedTitle = expectedTitle;
		this.titleStrategy = titleStrategy;
	}
	
	public By getLocator() {
		return locator;
	}
	
	public String getExpectedTitle() {
		return expectedTitle;
	}	
	
	public String getActualTitle(WebDriver driver) {
		return titleStrategy.getTitle(driver, locator);
	}		
}
