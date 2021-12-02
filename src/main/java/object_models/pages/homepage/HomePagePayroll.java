/**
 * 
 */
package object_models.pages.homepage;

import entities.PayGroup;
import entities.company.Company;
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
		initialiser.initialisePayroll();
		
		return null;
	}
	
	@Override
	public String getModuleName() {
		return "Payroll";
	}

}
