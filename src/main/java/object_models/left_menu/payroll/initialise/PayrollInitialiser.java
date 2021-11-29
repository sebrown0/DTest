/**
 * 
 */
package object_models.left_menu.payroll.initialise;

import entities.Company;
import object_models.pages.homepage.CompanyLoader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class PayrollInitialiser {
	private CompanyLoader companyLoader;
	private Company currentCompany;
	private Company forCompany;
		
	public PayrollInitialiser(CompanyLoader companyLoader) {
		this.companyLoader = companyLoader;
		setCurrentCompany();
	}

	public void initialisePayroll() {
		if(!(currentCompany.equals(forCompany))) {
//			closeForm();
			/*
			 * NOT EXPLICITLY SWITHING TO LeftNavBar.
			 * THIS IS NOT A CONTEXT (AT PRESENT)
			 */
			
			switchCompanies(forCompany); 	
			
		}
		/*
		 * 1. close form, and
		 * 2. check we're on the correct company.
		 * 3. if not load company.
		 */
//		clickInitialisePayroll();
	}
	
	private void switchCompanies(Company comp) {
		currentCompany = companyLoader.loadCompany(comp.getName());
	}
	
	private void setCurrentCompany() {
		currentCompany = companyLoader.getCurrentCompany();
	}
	
//	private CompanyLoader getCompanyLoader() {
//		return (CompanyLoader) hp;
//	}
}
