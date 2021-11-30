/**
 * 
 */
package object_models.helpers.company;

import context_manager.ContextManager;
import context_manager.ContextState;
import entities.Company;
import object_models.forms.ContainerAction;
import object_models.forms.FormModal;
import object_models.left_nav_bar.LeftNavBar;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Load the required company from the left nav-bar.
 * If there is a modal form open (meaning the path to nav-bar is blocked), 
 * close it first.
 */
public class LoadCompany {
	private Company forCompany;
	private ContextManager cm;
	private LeftNavBar leftNavBar;
	
	public LoadCompany(Company forCompany, CoreData coreData, LeftNavBar leftNavBar) {
		this.forCompany = forCompany;
		this.leftNavBar = leftNavBar;
		this.cm = coreData.getContextManager();
	}
	
	public Company loadCompany() {
		closeAnyOpenModalForms();
		return loadCompanyIfNecessary();
	}
	
	private void closeAnyOpenModalForms() {
		if(modalFormIsOpen()) {
			closeModalForm(cm.getCurrentContext());
		}
	}
	private boolean modalFormIsOpen() {		
		ContainerAction current = cm.getCurrentContext().getContinerAction();
		return (current instanceof FormModal) ? true : false;
	}
	private void closeModalForm(ContextState modalForm) {
		ContainerAction current = cm.getCurrentContext().getContinerAction();
		current.close();
	}
	private Company loadCompanyIfNecessary() {
		Company current = getCurrentCompany(); 
		if(current.equals(forCompany)) {
			return current;
		}else {
			return leftNavBar.loadCompany(forCompany.getName());
		}	
	}
	private Company getCurrentCompany() {
		return leftNavBar.getCompany();
	}
}