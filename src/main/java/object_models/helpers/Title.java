/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class Title implements PageTitle{
	private String expectedTitle;	
	private WebDriver driver;
	private By titleSelector;
	
	
	public Title(String expectedTitle, WebDriver driver, By titleSelector) {
		this.expectedTitle = expectedTitle;
		this.driver = driver;
		this.titleSelector = titleSelector;
	}

	@Override
	public String getExpected() {
		return expectedTitle;
	}

	@Override
	public String getActual() {
		WebElement e = driver.findElement(titleSelector);
		return e.getAttribute("textContent");
	}		
}
