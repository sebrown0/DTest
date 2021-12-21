/**
 * 
 */
package object_models.pages.homepage;

import java.util.Optional;

import entities.company.Company;
import entities.payroll.PayGroup;
import object_models.forms.ContainerAction;
import object_models.module_payroll.left_menu.payroll.GlobalAdjustments;
import object_models.module_payroll.left_menu.payroll.PayrollElement;
import object_models.module_payroll.left_menu.payroll.initialise.InitialisePayroll;
import object_models.module_payroll.left_menu.payroll.initialise.PayrollInitialiser;

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

	// Actions 
	public HomePagePayroll initialisePayroll(Company forCompany, PayGroup payGroup) {
		PayrollInitialiser initialiser = new PayrollInitialiser(this, forCompany, payGroup, super.getLeftMenu());		
		return initialiser.initialisePayroll();
	}
	
	public InitialisePayroll openInitialisePayroll() {
		return openOneOfMyElements(InitialisePayroll.class);
	}
	
	public GlobalAdjustments openGlobalAdjustments() {
		return openOneOfMyElements(GlobalAdjustments.class);
	}
	
	// Helpers
	@SuppressWarnings("unchecked")
	private <T extends PayrollElement> T openOneOfMyElements(Class<T> clazz){
		T initPay = null;
		Optional<ContainerAction> initPayCont =	leftMenu.clickAndLoad(clazz);
		if(initPayCont.isPresent()) {
			initPay = (T) initPayCont.get();					
		}
		return initPay;
	}
	
	// Getters and setters
	@Override
	public String getModuleName() {
		return "Payroll";
	}

}
