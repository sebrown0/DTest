/**
 * 
 */
package object_models.modules.personnel;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.contexts.Context;
import context_manager.contexts.ContextPersonnel;
import context_manager.states.StateFactorySetter;
import entities.company.Company;
import object_models.forms.ContainerAction;
import object_models.helpers.IFrame;
import object_models.helpers.title.PageTitle;
import object_models.left_menu.common.LeftMenu;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import object_models.pages.homepage.HomePagePersonnel;
import object_models.pages.homepage.loader.HomePageElements;
import object_models.panels.JsPanelHeaderBar;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.personnel.NavBarPersonnelElements;
import object_models.top_right_nav_bar.quick_links.QuickLink;
import object_models.top_right_nav_bar.quick_links.QuickLinkPersonnel;
import providers.ModuleNames;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class PersonnelModuleElements implements HomePageElements {
	private WebDriver driver;
	private CoreData coreData;
	private Company company;
	
	public PersonnelModuleElements(Company company) {
		this.company = company;
	}
	
	@Override
	public void setCoreData(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
	}
	
	@Override
	public NavBarElementStrategy getElementStrategy(ContextManager contextManager) {
		return new NavBarPersonnelElements(coreData);
	}

	@Override
	public QuickLink getQuickLinkToLoadModule() {
		return new QuickLinkPersonnel(driver);
	}

	@Override
	public String getModuleName() {
		return ModuleNames.PERSONNEL_NAME;
	}

	@Override
	public void setLeftMenuElements(LeftMenu menu) {
		LogManager.getLogger().error("Not implemented");
		
	}

	@Override
	public Context getContextForModule() {		
		return new ContextPersonnel(
				coreData, 
				new ContextIdGetter() {			
						@Override
						public ContextId getContextId() {							
							return new ContextId("Personnel Module", "Personnel Module");
						}
						@Override
						public String getContextExpectedName() {
							return "Personnel Module";
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
						System.out.println("PersonnelModuleElements.ContextPayroll.getMyContext() *** NOT IMPLENTED ***"); // TODO - remove or log 	
						return null;
					}					
				});	
	}
	
	@Override
	public HomePage getHomePage() {		
		return new HomePagePersonnel(coreData);
	}

	@Override
	public Company getCompany() {
		return company;
	}
}
