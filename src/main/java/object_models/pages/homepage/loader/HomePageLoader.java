/**
 * 
 */
package object_models.pages.homepage.loader;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.CallingState;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateModule;
import entities.company.Company;
import entities.company.LoaderCompany;
import exceptions.HomePageElementException;
import object_models.left_menu.common.LeftMenu;
import object_models.left_nav_bar.LeftNavBar;
import object_models.modules.helpers.ModuleChecker;
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
	
	public HomePageLoader(WebDriver driver, HomePageElements elements) throws HomePageElementException {
		if(elements == null) {
			throw new HomePageElementException("Elements is null");
		}
		this.driver = driver;
		this.elements = elements;		
		this.moduleName = elements.getModuleName();		
	}
		
	public abstract HomePage loadHomePage();
	
	public void initialiseLoader(CoreData coreData) {
		setCoreData(coreData);
		setHomePageElementsCoreData();
		setInitialStateOfContextManager();
		setNavBars();
	}
	private void setCoreData(CoreData coreData) {
		this.coreData = coreData;
		this.logger = coreData.getLogger();
		this.driver = coreData.getWebDriver();
		this.contextManager = coreData.getContextManager();		
	}	
	private void setHomePageElementsCoreData() {
		elements.setCoreData(coreData);
	}
	private void setInitialStateOfContextManager() {
		logger.debug("Setting initial state of Context Manager");
		contextManager.setLatestCallingState(new CallingState() {			
			@Override
			public State getState(ContextState context) {
				return new StateModule(context, driver);
			}
		});
		contextManager.setFirstContext(elements.getContextForModule());				
	}
	private void setNavBars() {
		leftNavBar = getLeftNavBar();
		rightNavBar = getTopRightNavBar();
	}		

	public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}
	
	protected boolean loadModule() {		
		if(ModuleChecker.isValidModuleName(moduleName) && !ModuleChecker.isCurrentModule(moduleName, driver)){			
			elements.getQuickLinkToLoadModule().clickMe();
			return true;
		}	
		return false;		
	}	
	protected boolean loadCompany() {
		Company forCompany = elements.getCompany();
		if(forCompany.equals(currentCompany)) {
			return false;
		}else {
			if(leftNavBar == null) {
				setNavBars();
			}
			LoaderCompany loader = new LoaderCompany(elements.getCompany(), coreData, leftNavBar);
			loader.loadCompany().ifPresent(c -> currentCompany = c);		
			return true;	
		}		
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