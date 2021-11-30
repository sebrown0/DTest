/**
 * 
 */
package object_models.pages;

import static providers.URIProvider.LOGIN_PAGE_URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import entities.User;
import object_models.modules.common.ModuleElements;
import object_models.modules.common.ModuleLoaderAtLogin;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.CoreDataLoader;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Load module from here and not HomePage.
 * 	Return the correct HomePage for the module.
 * @since 1.0
 *
 * Try to login a user and return the HomePage.
 */

public class UserLoginPage extends LoadablePage {
	private ModuleElements moduleElements;
	private CoreData coreData;
	private Logger logger = LogManager.getLogger();
	
	private By byUserName = By.name("user");
	private By byUserPassword = By.name("password");
	private By byBtnLogin = By.className("login100-form-btn");	
	
	// Just login.
	public UserLoginPage(WebDriver driver) {
		super(driver, "None", LOGIN_PAGE_URI);		
		
		setCoreData();
	}
	
	// Login with ModuleElements so a HomePage can be returned.
	public UserLoginPage(WebDriver driver, ModuleElements moduleElements) {
		super(driver, "None", LOGIN_PAGE_URI);
	
		setCoreData();
		setModuleElements(moduleElements);
	}
		
	private void setCoreData() {
		coreData = new CoreDataLoader(driver);
	}
	private void setModuleElements(ModuleElements moduleElements) {
		this.moduleElements = moduleElements;
		this.moduleElements.setCoreData(coreData);
	}
	
	public HomePage loginValidUser(User user) {
		driver.findElement(byUserName).sendKeys(user.getName());
		driver.findElement(byUserPassword).sendKeys(user.getPswd());
		driver.findElement(byBtnLogin).click();
		
		return getHomePageIfModuleSupplied();
	}
	
	private HomePage getHomePageIfModuleSupplied() {
		if(moduleElements == null) {
			logger.error("No module supplied");			
			return null;
		}else {
			return loadModule();
		}
	}
	
	private HomePage loadModule() {		
		ModuleLoaderAtLogin moduleLoader = new ModuleLoaderAtLogin(coreData, moduleElements);
		HomePage hp = moduleLoader.loadModule();			
		return hp;
	}
	
}
