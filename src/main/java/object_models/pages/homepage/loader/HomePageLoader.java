/**
 * 
 */
package object_models.pages.homepage.loader;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import entities.company.Company;
import entities.company.LoaderCompany;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.modules.common.ModuleChecker;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import object_models.top_right_nav_bar.common.TopRightNavBar;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

/*
 * Get a home page for 
 *  1. When a module is loaded (if it's the same module return same HP).
 *  2. A company is loaded (if it's the same company return same HP).
 *  3. Both of the above.
 */

public abstract class HomePageLoader {
	protected HomePageElements elements;
	protected HomePage hp;
	protected WebDriver driver;
	
	private LeftNavBar leftNavBar;
	private TopRightNavBar rightNavBar;
	
	private String moduleName;
	private Logger logger;
	private ContextManager contextManager;
	private CoreData coreData;
	private Company currentCompany;
	
	public HomePageLoader(WebDriver driver, HomePageElements elements) {
		this.driver = driver;
		this.elements = elements;		
		this.moduleName = elements.getModuleName(); //TODO
		this.currentCompany = elements.getCompany();
	}
	
//	public HomePageLoader(CoreData coreData, WebDriver driver, HomePageElements elements) {
//		this.coreData = coreData;
//		this.driver = driver;
//		this.elements = elements;		
//		this.moduleName = elements.getModuleName(); //TODO
//		this.currentCompany = elements.getCompany();
//	}

	public void setCoreData(CoreData coreData) {
		this.coreData = coreData;
		//TODO - redo
		this.contextManager = coreData.getContextManager();
		this.logger = coreData.getLogger();
		//TODO
		setNavBars();
	}

	public abstract HomePage loadHomePage();
		
	protected boolean loadModule() {
		if(ModuleChecker.isValidModuleName(moduleName) && !ModuleChecker.isCurrentModule(moduleName, driver)){
			elements.getQuickLinkToLoadModule().clickMe();
			return true;
		}	
		return false;		
	}	
	protected boolean loadCompany() {
		if(leftNavBar == null) {
			setNavBars();
		}
		LoaderCompany loader = new LoaderCompany(elements.getCompany(), coreData, leftNavBar);
		loader.loadCompany().ifPresent(c -> currentCompany = c);		
		return true;
	}

	private void setNavBars() {
		leftNavBar = getLeftNavBar();
		rightNavBar = getTopRightNavBar();
	}
	protected void initialiseHomePage(HomePage hp) {
		hp.setLeftNavBar(leftNavBar);
		hp.setTopRightNavBar(rightNavBar);
		hp.setLeftMenu(getLeftMenu());
		/*
		 * The company will be 1 of:
		 * 	1. [null] if no company was included in elements & it's not a new page.
		 * 	2. [the value passed in elements] if the required Company is equal to existing.
		 * 	3. [a new company] if the required company is not current, or is a new page.
		 */
		hp.setCurrentCompany(currentCompany);
	}
	private LeftNavBar getLeftNavBar() {
		logger.info("Creating left nav-bar for " + moduleName + " module");
		
		return new LeftNavBar(driver, contextManager);
	}	
	private TopRightNavBar getTopRightNavBar() {
		logger.info("Creating top-right nav-bar for " + moduleName + " module");
		
		TopRightNavBar topRightNavBar = new TopRightNavBar(driver, contextManager);		
		topRightNavBar.loadElements(elements.getElementStrategy(contextManager));
		return topRightNavBar;
	}
	
	private LeftMenu getLeftMenu() {
		logger.info("Creating left menu for " + moduleName + " module");
		
		LeftMenu leftMenu = new LeftMenu(coreData);				
		elements.setLeftMenuElements(leftMenu);
		return leftMenu;
	}
}
