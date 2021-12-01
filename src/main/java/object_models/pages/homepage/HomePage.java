/**
 * 
 */
package object_models.pages.homepage;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import entities.Company;
import object_models.helpers.DriverWait;
import object_models.helpers.company.LoadCompany;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.modules.common.ModuleElements;
import object_models.modules.common.ModuleLoader;
import object_models.modules.payroll.PayrollModuleElements;
import object_models.pages.Page;
import object_models.top_right_nav_bar.common.TopRightNavBar;;


/**
 * @author SteveBrown
 * @version 1.1
 * @since 1.0
 *
 */
public abstract class HomePage extends Page implements CoreData, CompanyLoader {
	private LeftNavBar leftNavBar;
	private TopRightNavBar topRightNavBar;
	private LeftMenu leftMenu;
		
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
		
	}
	
	public abstract String getModuleName();
	
	// Actions
//	public HomePage loadModule(ModuleElements moduleElements) {
//		/*
//		 * using existing
//		 * but will have to reset for new page load.
//		 */
//		this.moduleElements = moduleElements;
//		moduleElements.setCoreData(this);
//		ModuleLoader loader = new ModuleLoader(this, moduleElements);
//		return loader.loadModule(this, moduleElements.getModuleName());
//	}
	

	public void loadInitialCompany(Company company) {
		LoadCompany loader = new LoadCompany(company, this, leftNavBar);
		loader.loadCompany();
	}
	
	@Override //CompanyLoader
	public HomePage loadCompany(Company co) {
		LoadCompany loader = new LoadCompany(co, this, leftNavBar);
		Company comp = loader.loadCompany();
		
		//Have to get the current module.
		CoreData coreData = new CoreDataLoader(driver);
		ModuleElements moduleElements = new PayrollModuleElements(comp);
		moduleElements.setCoreData(coreData);
		return loadModule(moduleElements, coreData);
	}
	
	private HomePage loadModule(ModuleElements moduleElements, CoreData coreData) {		
		ModuleLoader moduleLoader = new ModuleLoader(coreData, moduleElements);
		HomePage hp = moduleLoader.loadModule(moduleElements.getModuleName());			
		return hp;
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
