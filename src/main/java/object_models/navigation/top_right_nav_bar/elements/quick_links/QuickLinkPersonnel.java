/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements.quick_links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public class QuickLinkPersonnel extends QuickLink{
	private static final By LOCATOR = By.xpath("/html/body/form/header/ul[4]/li[12]/div/div[2]/a[2]"); 
	
	public QuickLinkPersonnel(WebDriver driver) {
		super(driver, LOCATOR);
	}
}
