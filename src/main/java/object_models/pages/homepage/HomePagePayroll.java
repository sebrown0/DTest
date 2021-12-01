/**
 * 
 */
package object_models.pages.homepage;

import entities.Company;
import object_models.left_menu.payroll.initialise.PayrollInitialiser;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class HomePagePayroll extends HomePage {

	public HomePagePayroll(CoreData coreData) {
		super(coreData);		
	}

	public void initialisePayroll(Company forCompany) {
		PayrollInitialiser initialiser = new PayrollInitialiser(this, forCompany, super.getLeftMenu());
		initialiser.initialisePayroll();
	}
	
	@Override
	public String getModuleName() {
		return "Payroll";
	}

}
