/**
 * 
 */
package object_models.pages.homepage;

import static providers.PageTitleProvider.HOME_PAGE_TITLE;

import java.time.Duration;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import entities.company.Company;
import exceptions.HomePageElementException;
import factories.ModuleElementsFactory;
import object_models.forms.ContainerAction;
import object_models.forms.ModalCloser;
import object_models.helpers.DriverWait;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.pages.Page;
import object_models.pages.homepage.loader.ExistingHomePageLoader;
import object_models.top_right_nav_bar.common.NavBarElement;
import object_models.top_right_nav_bar.common.TopRightNavBar;;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Load company and get new HomePage if company loaded.
 * @version 1.2
 *  Handle HomePageElementException when trying to load HomePage.
 * @since 1.0
 *
 */
public abstract class HomePage extends Page implements CoreData, HomePageElement {
	protected LeftMenu leftMenu;
	
	private LeftNavBar leftNavBar;
	private TopRightNavBar topRightNavBar;	
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
	
	// Actions	
	@Override //HomePageElement
	public HomePage loadModule(String moduleName) {
		return loadHomePageFromExisting(moduleName, currentCompany);
	}	
	@Override //HomePageElement
	public HomePage loadCompany(Company co) {
		return loadHomePageFromExisting(getModuleName(), co);
	}	
	private HomePage loadHomePageFromExisting(String moduleName, Company co) {
		ModalCloser.closeAnyOpenModalForms(contextManager);
		ExistingHomePageLoader loader;
		try {
			loader = new ExistingHomePageLoader(
					coreData, 
					ModuleElementsFactory.getModuleElements(moduleName, co), 
					this
			);
			return loader.loadHomePage();
		} catch (HomePageElementException e) {
			logger.error("Cannot load home page from existing. Keeping existing home page");
		}	
		return this;
	}

	@Override //Page
	public void close() {
		driver.quit();
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
	public void setLeftNavBar(LeftNavBar leftNavBar) {
		this.leftNavBar = leftNavBar;
	}
	public void setTopRightNavBar(TopRightNavBar topRightNavBar) {
		this.topRightNavBar = topRightNavBar;
	}
	public void setLeftMenu(LeftMenu leftMenu) {
		this.leftMenu = leftMenu;
	}

	public Company getCurrentCompany() {
		return leftNavBar.getCompany();
	}
	
	public String waitForAndGetModuleName(String modName) {
		WebElement e = DriverWait.getElementAfterWaitForValue(driver, byXpathActualModuleName, Duration.ofSeconds(2), modName);
		return (e == null) ? "" : e.getAttribute("innerHTML");		
	}
	public String getActualModuleName() {		
		return driver.findElement(byXpathActualModuleName).getAttribute("innerHTML");
	}
	
	public Optional<ContainerAction> loadLeftMenuItem(Class<?> clazz) {
		return leftMenu.clickAndLoad(clazz);		
	}
	
	/*
	 * TODO - USE ABSTRACTIONS FOR ELEMENTS.
	 * Remove the getters below and replace with abstractions.
	 * This will make handling the contexts easier & more robust.
	 */
	public TopRightNavBar getTopRightNavBar() {
		return topRightNavBar;
	}	
	
	public Optional<NavBarElement> getTopRightNavBarElement(String elementName){
		return topRightNavBar.getNavBarElement(elementName);
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