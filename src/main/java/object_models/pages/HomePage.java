/**
 * 
 */
package object_models.pages;

import static resources.PageTitleProvider.HOME_PAGE_TITLE;

import org.openqa.selenium.WebDriver;

import object_models.navigation.top_right_nav_bar.TopRightNavBar;;


/**
 * @author SteveBrown
 *
 */
public class HomePage extends Page {
	private WebDriver driver;
	private TopRightNavBar topRightNavBar;
	
	private static final String MY_TITLE = HOME_PAGE_TITLE;	
	
	public HomePage(WebDriver driver) {		
		super(driver, MY_TITLE);		
		this.driver = driver;
		topRightNavBar = new TopRightNavBar(driver);
	}
	

	@Override
	public boolean isPageTitleCorrect() {
		return (driver.getTitle().equals(MY_TITLE)) ? true : false;
	}

	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}
	
}
