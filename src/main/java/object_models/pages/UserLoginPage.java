/**
 * 
 */
package object_models.pages;

import static providers.URIProvider.LOGIN_PAGE_URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import entities.User;
import object_models.modules.common.ModuleElements;

/**
 * @author Steve Brown
 *
 */

public class UserLoginPage extends LoadablePage {
	private ModuleElements moduleLoader;
	private ContextManager contextManager = new ContextManager();
	private By byUserName = By.name("user");
	private By byUserPassword = By.name("password");
	private By byBtnLogin = By.className("login100-form-btn");	
	
	// Just login.
	public UserLoginPage(WebDriver driver) {
		super(driver, null, LOGIN_PAGE_URI);		
	}
	// Login with ModuleElements so a HomePage can be returned.
	public UserLoginPage(WebDriver driver, ModuleElements moduleLoader) {
		super(driver, null, LOGIN_PAGE_URI);
		this.moduleLoader = moduleLoader;
	}
		
	public HomePage loginValidUser(User user) {
		driver.findElement(byUserName).sendKeys(user.getName());
		driver.findElement(byUserPassword).sendKeys(user.getPswd());
		driver.findElement(byBtnLogin).click();
		
		return getHomePageIfModuleSupplied();
	}
	
	public HomePage getHomePageIfModuleSupplied() {
		if(moduleLoader == null) {
			return null;
		}else {
			return new HomePage(driver, moduleLoader, contextManager);
		}
	}
}
