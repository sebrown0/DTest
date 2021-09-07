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
public class TitlePage  extends Title{	
	public TitlePage(String expectedTitle, WebDriver driver) {
		super(expectedTitle, driver, By.tagName("title"));
		System.out.println("TitlePanel");
	} 	
}
