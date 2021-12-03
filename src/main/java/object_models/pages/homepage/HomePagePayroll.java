/**
 * 
 */
package object_models.pages.homepage;

import entities.company.Company;
import entities.payroll.PayGroup;
import object_models.left_menu.payroll.initialise.PayrollInitialiser;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class HomePagePayroll extends HomePage {

	public HomePagePayroll(CoreData coreData) {
		super(coreData);		
	}

	public HomePagePayroll initialisePayroll(Company forCompany, PayGroup payGroup) {
		super.loadCompany(forCompany);
		PayrollInitialiser initialiser = new PayrollInitialiser(this, forCompany, payGroup, super.getLeftMenu());		
		return initialiser.initialisePayroll();
	}
	
	@Override
	public String getModuleName() {
		return "Payroll";
	}

}
