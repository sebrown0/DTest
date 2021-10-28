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
import object_models.forms.ContainerAction;
import object_models.helpers.IFrame;
import object_models.helpers.title.PageTitle;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll_only.LeftMenuPayroll;
import object_models.modules.common.ModuleElements;
import object_models.panels.JsPanelHeaderBar;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.payroll.NavBarPayrollElements;
import object_models.top_right_nav_bar.quick_links.QuickLink;
import object_models.top_right_nav_bar.quick_links.QuickLinkPayroll;
import providers.ModuleNames;

/**
 * @author Steve Brown
 *
 * The required elements of the payroll module.
 * 
 */
public class PayrollModuleLoader implements ModuleElements {
	private WebDriver driver;

	public PayrollModuleLoader(WebDriver driver) {
		this.driver = driver;		
	}

	@Override
	public Context getContextForModule(ContextManager contextManager) { 	
		// TODO - REMOVE ANONYMOUS CLASSES
		return new ContextPayroll(
				contextManager, 
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
									return contextManager;
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
	
//	@Override
//	public Context getContextForModule(ContextManager contextManager) { 	
//		return new ContextPayroll(
//				contextManager, 
//				new ContextIdGetter() {			
//						@Override
//						public ContextId getContextId() {							
//							return new ContextId("Payroll Module", "Payroll Module");
//						}
//
//						@Override
//						public String getContextExpectedName() {
//							return "Payroll Module";
//						}
//					}, null); // setting a NULL container action!				
//	}
	
	@Override
	public NavBarElementStrategy getElementStrategy(ContextManager contextManager) {
		return new NavBarPayrollElements(driver, contextManager);
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
		
}
