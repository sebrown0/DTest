/**
 * 
 */
package object_models.helpers.title;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class TitleHomePage extends Title{	
	public TitleHomePage(String expectedTitle, WebDriver driver) {
		super(expectedTitle, driver, By.xpath("html/body/form/header/div/div"));
	}
}
