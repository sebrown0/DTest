/**
 * 
 */
package object_models.pages;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.openqa.selenium.WebDriver;

import object_models.navigation.top_right_nav_bar.TopRightNavBar;;


/**
 * @author SteveBrown
 *
 */
public class HomePage extends Page {
	private TopRightNavBar topRightNavBar;
		
	public HomePage(WebDriver driver) {		
		super(driver, HOME_PAGE_TITLE);		
		this.driver = driver;
		topRightNavBar = new TopRightNavBar(driver);
	}

	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}
	
}
