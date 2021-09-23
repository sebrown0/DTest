/**
 * 
 */
package object_models.pages;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.modules.common.ModuleElements;
import object_models.modules.common.ModuleLoader;
import object_models.top_right_nav_bar.common.TopRightNavBar;;


/**
 * @author Steve Brown
 *
 */
public class HomePage extends Page {
	private LeftNavBar leftNavBar;
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;	
	private Logger logger = LogManager.getLogger();
	private WebDriver driver;
	
	private static By byXpathActualModuleName = By.xpath("html/body/form/header/div/div");
	public static final String PAGE_TITLE = HOME_PAGE_TITLE;
	
	public HomePage(WebDriver driver, ModuleElements moduleElements) {		
		super(driver, PAGE_TITLE);		
		this.driver = driver;
		loadModule(moduleElements);
	}
		
	// Actions
	public void loadModule(ModuleElements moduleElements) {
		if(moduleElements == null) {			
			logger.error("No module supplied");			
		}else {			
			ModuleLoader moduleLoader = new ModuleLoader(driver, moduleElements);
			leftNavBar = moduleLoader.setLeftNavBar();
			topRightNavBar = moduleLoader.setNavBar();
			leftMenu = moduleLoader.setLeftMenu();
		}		
	}
		
	@Override
	public void close() {
		driver.quit();
	}
	
	/*
	 * Getters Below
	 */
	public String getActualModuleName() {		
		return driver.findElement(byXpathActualModuleName).getAttribute("innerHTML");
	}
	public LeftNavBar getLeftNavBar() {
		return leftNavBar;
	}
	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}	
	public LeftMenu getLeftMenu() {
		return leftMenu;
	}
	public WebDriver getWebDriver() {
		return driver;
	}
}
