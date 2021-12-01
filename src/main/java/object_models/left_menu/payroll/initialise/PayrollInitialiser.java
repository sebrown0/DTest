/**
 * 
 */
package object_models.left_menu.payroll.initialise;

import java.util.Optional;

import controls.TextSelect;
import entities.Company;
import enums.control_names.PayrollControlNames;
import object_models.left_menu.common.LeftMenu;
import object_models.pages.homepage.CompanyLoader;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class PayrollInitialiser {
	private CompanyLoader companyLoader;
	private Company forCompany;
	private InitialisePayroll initPay;
	private LeftMenu leftMenu;
	private CoreData coreData;
	
	public PayrollInitialiser(CompanyLoader companyLoader, Company forCompany, LeftMenu leftMenu) {
		this.companyLoader = companyLoader;
		this.forCompany = forCompany;		
		this.leftMenu = leftMenu;
		this.coreData = (CoreData) companyLoader;
	}

	public void initialisePayroll() {
		loadCompany().ifPresentOrElse(
				c -> {
					openInitialisePayroll();
					checkCurrent();
//					clickInitialisePayroll();
				}, 
				new Runnable() {					
					@Override
					public void run() {
						coreData.getLogger().error("Could not initialise payroll.");						
					}
				}
		);			
	}
	
	private Optional<Company> loadCompany() {
		return Optional.ofNullable(companyLoader.loadCompany(forCompany));
	}
	
	private void openInitialisePayroll() {
		leftMenu
			.clickParent(InitialisePayroll.MENU_PARENT_NAME)
			.clickAndLoad(InitialisePayroll.class)
			.ifPresent(c -> initPay = (InitialisePayroll) c);
	}
	
	private void checkCurrent() {		
		String actualPayPeriod = initPay.getPayPeriod();
		System.out.println("A->" + actualPayPeriod); // TODO - remove or log
		System.out.println("R->" + forCompany.getPayGroup("Monthly Paygroup").getPayPeriod(10).getPayPeriodDateWithPeriodNum()); // TODO - remove or log 	
	}
//	public DialogOkCancel clickInitialisePayroll() {
//		Button init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
//		init.click();
//		return new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));
//	}
}
