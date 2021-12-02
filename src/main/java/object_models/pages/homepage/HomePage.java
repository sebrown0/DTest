/**
 * 
 */
package object_models.pages.homepage;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import entities.company.Company;
import object_models.forms.ModalCloser;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.modules.payroll.PayrollModuleElements;
import object_models.modules.personnel.PersonnelModuleElements;
import object_models.pages.Page;
import object_models.pages.homepage.loader.ExistingHomePageLoader;
import object_models.pages.homepage.loader.HomePageElements;
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
	private CoreData coreData;
	private ContextManager contextManager;
	private Logger logger;
	private WebDriver driver;
	
	private static By byXpathActualModuleName = By.xpath("html/body/form/header/div/div");	
	public static final String PAGE_TITLE = HOME_PAGE_TITLE;
	
	public HomePage(CoreData coreData) {		
		super(coreData.getWebDriver(), PAGE_TITLE);		
		
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.contextManager = coreData.getContextManager();
		this.logger = coreData.getLogger();		
	}
	
	public abstract String getModuleName();
	
	private HomePageElements getElements(Company co) {
		HomePageElements elements;
		if(getModuleName().equals("Payroll")){
			elements = new PayrollModuleElements(co);	
		}else {
			elements = new PersonnelModuleElements(co);
		}
		//giving elements existing CM
		elements.setCoreData(coreData);
		return elements;
	}
	public HomePage loadModule() {
		ModalCloser.closeAnyOpenModalForms(contextManager);
		ExistingHomePageLoader loader = new ExistingHomePageLoader(coreData, driver, getElements(currentCompany), this);	
		return loader.loadHomePage();
	}
	// Actions
	@Override //CompanyLoader
	public HomePage loadCompany(Company co) {
		ModalCloser.closeAnyOpenModalForms(contextManager);
		ExistingHomePageLoader loader = new ExistingHomePageLoader(coreData, driver, getElements(co), this);	
		return loader.loadHomePage();
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
	
	@Override
	public int hashCode() {
		int result = 1;
		final int prime = 31;
		result = prime * result + ((currentCompany == null) ? 0 : currentCompany.hashCode());
		return result;
	}
	
	/*
	 * Getters & Setters Below
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
	
	public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}

}
//@Override //CompanyLoader
//public HomePage loadCompany(Company co) {		
//if(co != null) {
//	if(currentCompany.equals(co)) {
//		System.out.println("Is current company. Not reloading"); // TODO - remove or log 	
//		logger.info("Is current company. Not reloading");
//		return this;
//	}else {
//		System.out.println("Loading new comp"); // TODO - remove or log
//		logger.info("Loading new company [" + co.getName() + "]");
//		LoadCompany loader = new LoadCompany(co, this, leftNavBar);
//		currentCompany = loader.loadCompany();			
//		return HomePageLoader.loadHomePage(getModuleName(), currentCompany, driver);	
//	}					
//}else {
//	logger.error("Cannot load null company");
//	return this;
//}		
//}		