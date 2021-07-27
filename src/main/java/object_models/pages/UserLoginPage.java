/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.User;

/**
 * @author SteveBrown
 *
 */
public class UserLoginPage extends LoadablePage {
	private By byUserName = By.name("user");
	private By byUserPassword = By.name("password");
	private By byBtnLogin = By.className("login100-form-btn");
	
	private static final String MY_TITLE = "Dakar Software Systems";
	private static final String MY_URI = "https://demo2.dakarhr.com/";
	
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
