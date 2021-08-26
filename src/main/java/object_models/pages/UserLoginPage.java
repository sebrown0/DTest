/**
 * 
 */
package object_models.pages;

import static providers.PageTitleProvider.LOGIN_PAGE_TITLE;
import static providers.URIProvider.LOGIN_PAGE_URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.User;
import object_models.modules.ModuleLoader;

/**
 * @author Steve Brown
 *
 */

public class UserLoginPage extends LoadablePage {
	private ModuleLoader moduleLoader;
	private By byUserName = By.name("user");
	private By byUserPassword = By.name("password");
	private By byBtnLogin = By.className("login100-form-btn");	
	
	public UserLoginPage(WebDriver driver) {
		super(driver, LOGIN_PAGE_TITLE, LOGIN_PAGE_URI);		
	}
	
	public UserLoginPage(WebDriver driver, ModuleLoader moduleLoader) {
		super(driver, LOGIN_PAGE_TITLE, LOGIN_PAGE_URI);
		this.moduleLoader = moduleLoader;
	}
		
	public HomePage loginValidUser(User user) {		
		driver.findElement(byUserName).sendKeys(user.getName());
		driver.findElement(byUserPassword).sendKeys(user.getPswd());
		driver.findElement(byBtnLogin).click();
		
		return new HomePage(driver, moduleLoader);
	}

}
