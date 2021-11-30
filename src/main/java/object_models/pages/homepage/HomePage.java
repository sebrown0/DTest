/**
 * 
 */
package object_models.pages.homepage;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import entities.Company;
import object_models.helpers.company.LoadCompany;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.modules.common.ModuleElements;
import object_models.modules.common.ModuleLoader;
import object_models.pages.Page;
import object_models.top_right_nav_bar.common.TopRightNavBar;;


/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class HomePage extends Page implements CoreData, CompanyLoader {
	private LeftNavBar leftNavBar;
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;	
	private ContextManager contextManager;
	private Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Company currentCompany;
	
	private static By byXpathActualModuleName = By.xpath("html/body/form/header/div/div");
	
	public static final String PAGE_TITLE = HOME_PAGE_TITLE;
	
	public HomePage(WebDriver driver, ModuleElements moduleElements, ContextManager contextManager) {		
		super(driver, PAGE_TITLE);		
		
		this.driver = driver;
		this.contextManager = contextManager;
		
		loadModule(moduleElements);
		setCurrentCompany();
	}
		
	// Actions
	public void loadModule(ModuleElements moduleElements) {
		if(moduleElements == null) {			
			logger.error("No module supplied");			
		}else {			
			ModuleLoader moduleLoader = new ModuleLoader(this, moduleElements);
			leftNavBar = moduleLoader.setLeftNavBar(contextManager);
			topRightNavBar = moduleLoader.setNavBar(contextManager);
			leftMenu = moduleLoader.setLeftMenu(contextManager);
		}		
	}

	private void setCurrentCompany() {
		currentCompany = leftNavBar.getCompany();
		System.out.println("hp->setCurrentCompany" + currentCompany.getName()); // TODO - remove or log 	
	}

	@Override //CompanyLoader
	public Company loadCompany(Company co) {
		LoadCompany loader = new LoadCompany(co, this, leftNavBar);
		return loader.loadCompany();
	}
	@Override //CompanyLoader
	public Company getCurrentCompany() {
		return leftNavBar.getCompany();
	}
	
	@Override //Page
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
	
	@Override //CoreData
	public WebDriver getWebDriver() {
		return driver;
	}
	@Override //CoreData
	public ContextManager getContextManager() {
		return contextManager;
	}
	@Override //CoreData
	public Logger getLogger() {		
		return logger;
	}

}
