/**
 * 
 */
package object_models.top_right_nav_bar.all_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.helpers.Closable;
import object_models.pages.homepage.CoreData;
import object_models.reports.VisualReports;
import object_models.strategies.click.ClickUsingJavaScript;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author SteveBrown
 *
 */
public class NavBarVisualReports extends NavBarElement {
	public static final String ORIGINAL_NAME = "Visual Reports";
	
	public NavBarVisualReports(CoreData coreData) {
		super(coreData, ORIGINAL_NAME);
	}

	@Override
	public Closable clickElement() {
		WebElement el = super.getNavBar().findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-bar-chart')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return new VisualReports(super.coreData);
	}
}
