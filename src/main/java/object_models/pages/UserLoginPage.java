/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public class UserLoginPage extends LoadablePage {
	private WebDriver driver;
	private By byUserName = By.name("user");
	private By byUserPassword = By.name("password");
	private By byBtnLogin = By.className("login100-form-btn");
	
	private static final String MY_TITLE = "Dakar Software Systems";
	private static final String MY_URI = "https://demo2.dakarhr.com/";
	
	public UserLoginPage(WebDriver driver) {
		super(driver, MY_TITLE, MY_URI);
		this.driver = driver;
		
	}
		
	public HomePage loginValidUser(String userName, String userPswd) {		
		driver.findElement(byUserName).sendKeys(userName);
		driver.findElement(byUserPassword).sendKeys(userPswd);
		driver.findElement(byBtnLogin).click();
		
		return new HomePage(driver);
	}

}
