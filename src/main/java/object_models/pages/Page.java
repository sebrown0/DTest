/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.WebDriver;

import object_models.helpers.ChildElement;
import object_models.helpers.PageTitle;

/**
 * @author Steve Brown
 *
 */
public class Page implements PageTitle, ChildElement {
	protected WebDriver driver;
	private PageTitle title;	

	public Page(WebDriver driver, PageTitle title) {
		this.driver = driver;
		this.title = title;		
	}

	@Override
	public String getExpected() {
		return title.getExpected();
	}

	@Override
	public String getActual() {
		return title.getActual();
	}
	
	// WRONG - SHOULD NOT BE TESTING HERE!!!!!!!!
//	@Override 
//	public boolean isPageTitleCorrect() {		
//		return (driver.getTitle().equals(title)) ? true : false;
//	}
	
}
