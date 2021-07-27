/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.WebDriver;

import object_models.helpers.PageTitle;

/**
 * @author SteveBrown
 *
 */
public class Page implements PageTitle {
	protected WebDriver driver;
	protected String title;	

	public Page(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;		
	}
	
	@Override
	public boolean isPageTitleCorrect() {		
		return (driver.getTitle().equals(title)) ? true : false;
	}
	
}
