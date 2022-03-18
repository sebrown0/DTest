/**
 * 
 */
package object_models.modules.common.nav.nav_bar_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.helpers.Closable;
import object_models.pages.homepage.CoreData;
import object_models.reports.DakarIntelligence;
import object_models.strategies.click.ClickUsingJavaScript;
import object_models.top_right_nav_bar.common.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarDakarIntelligence extends NavBarElement { 	 
	public static final String ORIGINAL_NAME = "Dakar Intelligence";
	
	public NavBarDakarIntelligence(CoreData coreData) {
		super(coreData, ORIGINAL_NAME);
	}
	
	@Override
	public Closable clickElement() {
		WebElement el = super.getNavBar().findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-server')]"));
		ClickUsingJavaScript.performClick(driver, el);
		return new DakarIntelligence(super.coreData);
	}
}
