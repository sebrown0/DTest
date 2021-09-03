/**
 * 
 */
package object_models.strategies.title;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * Get the element's innerHTML attribute.
 */

/*
 * 
 * REMOVE
 * 
 */
public class ZZZ_TitleInInnerHTML implements ZZZ_TitleStrategy{
	private WebDriver driver;
	private By titleLocator;
		
	public ZZZ_TitleInInnerHTML(WebDriver driver, By titleLocator) {
		this.driver = driver;
		this.titleLocator = titleLocator;
	}

	@Override
	public String getTitle(WebDriver driver, By titleLocator) {		
		WebElement we = driver.findElement(titleLocator); 
		return we.getAttribute("innerHTML");
	}

	@Override
	public String getTitle() {
		WebElement we = driver.findElement(titleLocator); 
		return we.getAttribute("innerHTML");
	}

}
