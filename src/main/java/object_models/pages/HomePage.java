/**
 * 
 */
package object_models.pages;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.modules.ModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;;


/**
 * @author Steve Brown
 *
 */
public class HomePage extends Page {
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;
	private By byXpathModuleName = By.xpath("html/body/form/header/div/div");
	private Logger logger = LogManager.getLogger();
	
	public HomePage(WebDriver driver, ModuleLoader moduleLoader) {		
		super(driver, HOME_PAGE_TITLE);		
		
		topRightNavBar = new TopRightNavBar(driver);
		leftMenu = new LeftMenu(driver);
		loadModule(moduleLoader);		
	}
	
	public void loadModule(ModuleLoader moduleLoader) {
		if(moduleLoader == null) {			
			logger.error("No module supplied");
			
		}else {
			moduleLoader.loadModule(topRightNavBar, leftMenu);	
		}		
	}
		
	/*
	 * Getters Below
	 */
	public String getModuleName() {		
		return driver.findElement(byXpathModuleName).getAttribute("innerHTML");
	}
	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}	
	public LeftMenu getLeftMenu() {
		return leftMenu;
	}
//	public QuickLink getQuickLinks() {
//		return topRightNavBar
//	}
}
