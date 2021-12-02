/**
 * 
 */
package object_models.modules.payroll;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.contexts.Context;
import context_manager.contexts.ContextPayroll;
import context_manager.states.StateFactorySetter;
import entities.company.Company;
import object_models.forms.ContainerAction;
import object_models.helpers.IFrame;
import object_models.helpers.title.PageTitle;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll_only.LeftMenuPayroll;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import object_models.pages.homepage.HomePagePayroll;
import object_models.pages.homepage.loader.HomePageElements;
import object_models.panels.JsPanelHeaderBar;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.payroll.NavBarPayrollElements;
import object_models.top_right_nav_bar.quick_links.QuickLink;
import object_models.top_right_nav_bar.quick_links.QuickLinkPayroll;
import providers.ModuleNames;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Get the HomePage from ModuleElements.
 * @since 1.0
 *
 * The required elements of the payroll module.
 * 
 */
public class PayrollModuleElements implements HomePageElements {
	private WebDriver driver;
	private CoreData coreData;
	private Company company;
		
	public PayrollModuleElements(Company company) {
		this.company = company;
	}

	@Override
	public void setCoreData(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
	}

	@Override
	public Context getContextForModule() { 	
		// TODO - REMOVE ANONYMOUS CLASSES
		return new ContextPayroll(
				coreData, 
				new ContextIdGetter() {			
						@Override
						public ContextId getContextId() {							
							return new ContextId("Payroll Module", "Payroll Module");
						}
						@Override
						public String getContextExpectedName() {
							return "Payroll Module";
						}
					}, 
				new ContainerAction() {
						@Override
						public StateFactorySetter getStateFactorySetter() {
							// TODO Auto-generated method stub
							return new StateFactorySetter() {							
								@Override
								public ContextManager getContextManager() {
									return coreData.getContextManager();
								}
								@Override
								public WebDriver getWebDriver() {
									return driver;
								}							
								@Override
								public IFrame getIFrame() {
									return null;
								}
								@Override
								public JsPanelHeaderBar setJsPanelHeaderBar() {
									return null;
								}							
							};
						}
						
					@Override
					public void close() {
						LogManager.getLogger().error("*** NOT IMPLEMENTED ***");					
					}					
					@Override
					public PageTitle getTitle() {
						LogManager.getLogger().error("*** NOT IMPLEMENTED ***");
						return null;
					}

					@Override
					public ContextState getMyContext() {
						LogManager.getLogger().error("*** NOT IMPLENTED ***");
						System.out.println("PayrollModuleLoader.ContextPayroll.getMyContext() *** NOT IMPLENTED ***"); // TODO - remove or log 	
						return null;
					}					
				});				
	}
		
	@Override
	public NavBarElementStrategy getElementStrategy(ContextManager contextManager) {
		return new NavBarPayrollElements(coreData);
	}

	@Override
	public QuickLink getQuickLinkToLoadModule() {
		return new QuickLinkPayroll(driver);
	}

	@Override
	public String getModuleName() {
		return ModuleNames.PAYROLL_NAME;
	}

	@Override
	public void setLeftMenuElements(LeftMenu menu) {
		menu.setElements(new LeftMenuPayroll());
	}

	@Override
	public HomePage getHomePage() {
		return new HomePagePayroll(coreData);
	}

	@Override
	public Company getCompany() {
		return company;
	}
		
}
