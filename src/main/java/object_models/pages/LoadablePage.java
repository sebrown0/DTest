/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.WebDriver;

import exceptions.IncorrectPageException;
import object_models.helpers.LoadPage;

/**
 * @author Steve Brown
 *
 * -- Only used for Login --
 */
public class LoadablePage extends Page implements LoadPage {		
	private String uri;

	public LoadablePage(WebDriver driver, String expectedTitle, String uri) {
		super(driver, null);
		this.uri = uri;
		loadPage();
	}
		
	private void loadPage() {		
		try {
			loadUsingURI();
		} catch (IncorrectPageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadUsingURI() throws IncorrectPageException {
		driver.get(uri);
//		if (!isPageTitleCorrect()) {
//			throw new IncorrectPageException("Incorrect page");
//		}
	}

	@Override
	public void close() {
	
	}
	

}
