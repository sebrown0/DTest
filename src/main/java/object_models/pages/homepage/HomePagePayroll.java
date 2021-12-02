/**
 * 
 */
package object_models.pages.homepage;

import entities.Company;
import entities.PayGroup;
import object_models.left_menu.payroll.initialise.PayrollInitialiser;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class HomePagePayroll extends HomePage {

	public HomePagePayroll(CoreData coreData, Company co) {
		super(coreData, co);		
	}

	public void initialisePayroll(Company forCompany, PayGroup payGroup) {
		PayrollInitialiser initialiser = new PayrollInitialiser(this, forCompany, payGroup, super.getLeftMenu());		
		initialiser.initialisePayroll();		
	}
	
	@Override
	public String getModuleName() {
		return "Payroll";
	}

}
