/**
 * 
 */
package object_models.pages.homepage;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import entities.Company;
import object_models.helpers.company.LoadCompany;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.pages.Page;
import object_models.top_right_nav_bar.common.TopRightNavBar;;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Load company and get new HomePage if company loaded.
 * @since 1.0
 *
 */
public abstract class HomePage extends Page implements CoreData, CompanyLoader {
	private LeftNavBar leftNavBar;
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;
	private Company currentCompany;
		
	//CoreData
	private ContextManager contextManager;
	private Logger logger;
	private WebDriver driver;
	
	private static By byXpathActualModuleName = By.xpath("html/body/form/header/div/div");
	
	public static final String PAGE_TITLE = HOME_PAGE_TITLE;
	
	public HomePage(CoreData coreData, Company co) {		
		super(coreData.getWebDriver(), PAGE_TITLE);		
		
		this.driver = coreData.getWebDriver();
		this.contextManager = coreData.getContextManager();
		this.logger = coreData.getLogger();		
		this.currentCompany = co;
	}
	
	public abstract String getModuleName();
	
	// Actions	
	@Override //CompanyLoader
	public HomePage loadCompany(Company co) {		
		if(co != null) {
			if(currentCompany.equals(co)) {
				logger.info("Is current company. Not reloading");
				return this;
			}else {
				logger.info("Loading new company [" + co.getName() + "]");
				LoadCompany loader = new LoadCompany(co, this, leftNavBar);
				currentCompany = loader.loadCompany();			
				return HomePageLoader.loadHomePage(getModuleName(), currentCompany, driver);	
			}					
		}else {
			logger.error("Cannot load null company");
			return this;
		}		
	}			
	@Override //CompanyLoader
	public Company getCurrentCompany() {
		return leftNavBar.getCompany();
	}
	
	@Override //Page
	public void close() {
		driver.quit();
	}
	
	public void setLeftNavBar(LeftNavBar leftNavBar) {
		this.leftNavBar = leftNavBar;
	}
	public void setTopRightNavBar(TopRightNavBar topRightNavBar) {
		this.topRightNavBar = topRightNavBar;
	}
	public void setLeftMenu(LeftMenu leftMenu) {
		this.leftMenu = leftMenu;
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
