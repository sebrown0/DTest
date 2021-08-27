/**
 * 
 */
package object_models.pages;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.modules.ModuleElements;
import object_models.modules.ModuleLoader;
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;;


/**
 * @author Steve Brown
 *
 */
public class HomePage extends Page {
	private TopRightNavBar topRightNavBar;
	private LeftMenuPayroll leftMenu;
	private By byXpathActualModuleName = By.xpath("html/body/form/header/div/div");
	private Logger logger = LogManager.getLogger();
	
	public HomePage(WebDriver driver, ModuleElements moduleElements) {		
		super(driver, HOME_PAGE_TITLE);		
		
		topRightNavBar = new TopRightNavBar(driver);
		leftMenu = new LeftMenuPayroll(driver);
		loadModule(moduleElements);		
	}
	
	public void loadModule(ModuleElements moduleElements) {
		if(moduleElements == null) {			
			logger.error("No module supplied");			
		}else {
			ModuleLoader moduleLoader = new ModuleLoader(driver, moduleElements);
			moduleLoader.loadModule(topRightNavBar, leftMenu);	
		}		
	}
		
	/*
	 * Getters Below
	 */
	public String getActualModuleName() {		
		return driver.findElement(byXpathActualModuleName).getAttribute("innerHTML");
	}
	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}	
	public LeftMenuPayroll getLeftMenu() {
		return leftMenu;
	}
//	public QuickLink getQuickLinks() {
//		return topRightNavBar
//	}
}
