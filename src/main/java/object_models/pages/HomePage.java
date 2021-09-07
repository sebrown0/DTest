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
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;;


/**
 * @author Steve Brown
 *
 */
public class HomePage extends Page {
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;	
	private Logger logger = LogManager.getLogger();
	
	private static By byXpathActualModuleName = By.xpath("html/body/form/header/div/div");
	public static final String PAGE_TITLE = HOME_PAGE_TITLE;
	
	public HomePage(WebDriver driver, ModuleElements moduleElements) {		
		super(driver, PAGE_TITLE);		

		loadModule(moduleElements);
	}
		
	public void loadModule(ModuleElements moduleElements) {
		if(moduleElements == null) {			
			logger.error("No module supplied");			
		}else {			
			ModuleLoader moduleLoader = new ModuleLoader(driver, moduleElements);
			topRightNavBar = moduleLoader.setNavBar();
			leftMenu = moduleLoader.setLeftMenu();
		}		
	}
		
	@Override
	public void close() {
	
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
	public LeftMenu getLeftMenu() {
		return leftMenu;
	}
}
