/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.User;
import static resources.PageTitleProvider.LOGIN_PAGE_TITLE;
import static resources.URIProvider.LOGIN_PAGE_URI;

/**
 * @author SteveBrown
 *
 */

public class UserLoginPage extends LoadablePage {
	private By byUserName = By.name("user");
	private By byUserPassword = By.name("password");
	private By byBtnLogin = By.className("login100-form-btn");
	
	private static final String MY_TITLE = LOGIN_PAGE_TITLE;
	private static final String MY_URI = LOGIN_PAGE_URI;
	
	public UserLoginPage(WebDriver driver) {
		super(driver, MY_TITLE, MY_URI);
	}
		
	public HomePage loginValidUser(User user) {		
		driver.findElement(byUserName).sendKeys(user.getName());
		driver.findElement(byUserPassword).sendKeys(user.getPswd());
		driver.findElement(byBtnLogin).click();
		
		return new HomePage(driver);
	}

}
