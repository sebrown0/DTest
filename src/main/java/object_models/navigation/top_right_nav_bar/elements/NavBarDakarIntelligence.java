/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.navigation.NavBarElement;
import object_models.pages.DakarIntelligence;
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class NavBarDakarIntelligence extends NavBarElement { 	 
	public static final String ORIGINAL_NAME = "Dakar Intelligence";
	
	public NavBarDakarIntelligence(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
	
	@Override
	public ChildElement clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-server')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return new DakarIntelligence(driver, null);
	}
}
