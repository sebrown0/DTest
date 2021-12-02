/**
 * 
 */
package entities.company;

import java.util.Optional;

import org.apache.logging.log4j.Logger;

import context_manager.ContextManager;
import object_models.forms.ModalCloser;
import object_models.left_nav_bar.LeftNavBar;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Add try catch to loadCompany().
 * @since 1.0
 * 
 * Load the required company from the left nav-bar.
 * If there is a modal form open (meaning the path to nav-bar is blocked), 
 * close it first.
 */
public class LoaderCompany {
	private Company forCompany;
	private ContextManager cm;
	private LeftNavBar leftNavBar;
	private Logger logger;
	
	public LoaderCompany(Company forCompany, CoreData coreData, LeftNavBar leftNavBar) {
		this.forCompany = forCompany;
		this.leftNavBar = leftNavBar;
		this.cm = coreData.getContextManager();
		this.logger = coreData.getLogger();
	}
	
	public Optional<Company> loadCompany() {		
		if(forCompany != null) {
			try {
//				ModalCloser.closeAnyOpenModalForms(cm);
//				closeAnyOpenModalForms();
				return Optional.ofNullable(leftNavBar.loadCompany(forCompany.getName()));	
			} catch (Exception e) {
				logger.error("Error loading company");
				return Optional.empty();
			}		
		}
		return null;			
	}
	
//	private void closeAnyOpenModalForms() {
//		try {
//			if(modalFormIsOpen()) {
//				closeModalForm(cm.getCurrentContext());
//			}	
//		} catch (Exception e) {
//			// Nothing. Assume no open forms.
//		}		
//	}
//	private boolean modalFormIsOpen() {		
//		ContainerAction current = cm.getCurrentContext().getContinerAction();
//		return (current instanceof FormModal) ? true : false;
//	}
//	private void closeModalForm(ContextState modalForm) {
//		ContainerAction current = cm.getCurrentContext().getContinerAction();
//		current.close();
//	}
	
	
	
	
	
//	public Company loadCompany(Company forCompany) {		
//		Company currentCompany = null;
//		if(forCompany != null) {
//			if(forCompany.equals(forCompany)) {
//				System.out.println("Is current company. Not reloading"); // TODO - remove or log 	
//				logger.info("Is current company. Not reloading");				
//			}else {
//				System.out.println("Loading new comp"); // TODO - remove or log
//				logger.info("Loading new company [" + forCompany.getName() + "]");			
//				currentCompany = loadCompany();					
//			}					
//		}else {
//			logger.error("Cannot load null company");			
//		}				
//		return currentCompany;
//	}			
	
//	public Company loadCompany() {
//		if(forCompany != null) {
//			try {
//				closeAnyOpenModalForms();
//				return loadCompanyIfNecessary();	
//			} catch (Exception e) {
//				logger.error("Error loading company");
//				return null;
//			}	
//		}else {
//			logger.error("Cannot load [NULL] company!");
//			return null;
//		}		
//	}
//	private Company loadCompanyIfNecessary() {
//		Company current = getCurrentCompany(); 
//		if(current.equals(forCompany)) {
//			return current;
//		}else {
//			return leftNavBar.loadCompany(forCompany.getName());
//		}	
//	}
//	private Company getCurrentCompany() {
//		return leftNavBar.getCompany();
//	}
}
