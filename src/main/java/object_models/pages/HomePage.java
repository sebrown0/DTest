/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public class HomePage extends Page {
	private WebDriver driver;
	private static final String MY_TITLE = "Dakar Software Systems - Demo";	
	
//	private By byUserName = By.name("user");
//	private By byUserPassword = By.name("password");
//	private By byBtnLogin = By.className("login100-form-btn");
	
	public HomePage(WebDriver driver) {		
		super(driver, MY_TITLE);
		this.driver = driver;
	}

	@Override
	public boolean isPageTitleCorrect() {
		return (driver.getTitle().equals(MY_TITLE)) ? true : false;
	}
	
}
