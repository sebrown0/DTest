/**
 * 
 */
package object_models.pages;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.modules.ModuleLoader;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;;


/**
 * @author SteveBrown
 *
 */
public class HomePage extends Page {
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;
	private By byXpathModuleName = By.xpath("html/body/form/header/div/div");
	private ModuleLoader moduleLoader;
	
	public HomePage(WebDriver driver, ModuleLoader moduleLoader) {		
		super(driver, HOME_PAGE_TITLE);		

		this.moduleLoader = moduleLoader;
		
		implicitlyWaitForThePageToLoad();
		loadModuleIfSuppliedOrDefault();
		createNavAndMenus();
	}

	private void implicitlyWaitForThePageToLoad() {
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}			
	}
	
	private void loadModuleIfSuppliedOrDefault() {
		if(moduleLoader == null) {			
			try {
				moduleLoader = new PayrollModuleLoader(driver);
			} catch (NullDriverException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		moduleLoader.loadModule();
	}
	
	private void createNavAndMenus() {
		topRightNavBar = new TopRightNavBar(driver, moduleLoader.getElementStrategy());
		leftMenu = new LeftMenu(driver);
	}
	
	public String getModuleName() {		
		return driver.findElement(byXpathModuleName).getAttribute("innerHTML");
	}

	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}
	
	public LeftMenu getLeftMenu() {
		return leftMenu;
	}
}
