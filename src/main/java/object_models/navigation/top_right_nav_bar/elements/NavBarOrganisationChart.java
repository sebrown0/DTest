/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.helpers.NoElement;
import object_models.navigation.NavBarElement;
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class NavBarOrganisationChart extends NavBarElement {
	public static final String ORIGINAL_NAME = "Organisation Chart";
	
	public NavBarOrganisationChart(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}
			
	@Override
	public ChildElement clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-sitemap')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return new NoElement(ORIGINAL_NAME);
	}
}
