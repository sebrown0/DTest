/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.ChildElement;
import object_models.navigation.NavBarElement;
import object_models.pages.EmployeeGridView;
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author SteveBrown
 *
 */
public class NavBarVisualReports extends NavBarElement {
	public static final String ORIGINAL_NAME = "Visual Reports";
	
	public NavBarVisualReports(WebDriver driver) {
		super(driver, ORIGINAL_NAME);
	}

	@Override
	public ChildElement clickElement() {
		WebElement el = super.navBar.findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-bar-chart')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return new EmployeeGridView(driver);
	}
}
