/**
 * 
 */
package object_models.left_menu.payroll.initialise;

import java.util.Optional;

import org.apache.logging.log4j.Logger;

import controls.Button;
import entities.Company;
import entities.PayGroup;
import exceptions.StaleAnchorException;
import object_models.dialog.DialogOkCancel;
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
	private PayGroup forPayGroup;
	private InitialisePayroll initPay;
	private LeftMenu leftMenu;
	private CoreData coreData;
	private Logger logger;
	
	public PayrollInitialiser(CompanyLoader companyLoader, Company forCompany, PayGroup forPayGroup, LeftMenu leftMenu) {
		this.companyLoader = companyLoader;
		this.forCompany = forCompany;
		this.forPayGroup = forPayGroup;
		this.leftMenu = leftMenu;
		this.coreData = (CoreData) companyLoader;
		this.logger = this.coreData.getLogger();
	}

	public void initialisePayroll() {
		if(loadCompany().isPresent()) {
			openInitialisePayroll();
			if(actualIsRequired()) {
				if(notAlreadyInitialised()) {
					
					DialogOkCancel okCancel = initPay.clickInitialisePayroll();
					okCancel.clickCancel();
					
					System.out.println("intitialise"); // TODO - remove or log
				}else {
					logger.info("The payroll is already intitialised");
					System.out.println("The payroll is already intitialised"); // TODO - remove or log 	
				}						
			}
		}else {
			coreData.getLogger().error("Could not initialise payroll.");			
		}		
	}
	
	private Optional<Company> loadCompany() {
//		return Optional.ofNullable(companyLoader.loadCompany(forCompany));
		return null;
	}
	
	private void openInitialisePayroll() {
		/*
		 * catch
		 */
		try {
			leftMenu
			.clickAndLoad(InitialisePayroll.class)
			.ifPresent(c -> initPay = (InitialisePayroll) c);
		} catch (StaleAnchorException e) {
			System.out.println("SHOULD HAVE REMAPPED ANCHS. TRY AGAIN"); // TODO - remove or log 	
			openInitialisePayroll();
			System.out.println("Tried again"); // TODO - remove or log 	
		}

	}
	
	private boolean actualIsRequired() {
		boolean res = true;
		String actualPayPeriod = initPay.getPayPeriod();
		String requiredPayPeriod = forPayGroup.getCurrentPayPeriod().getPayPeriodDateWithPeriodNum();
		String actualPayGroup = initPay.getPayGroup();
				  	
		if(formValuesNotEqualToRequired(actualPayPeriod, requiredPayPeriod, actualPayGroup)) { 
			logDifferences(actualPayPeriod, requiredPayPeriod);
			res = false;
		}
		return res;
	}
	
	private boolean formValuesNotEqualToRequired(String actualPayPeriod, String requiredPayPeriod, String actualPayGroup) {
		boolean a = requiredPayPeriod.equals(actualPayPeriod);
		boolean b = forPayGroup.getPayGroupName().equals(actualPayGroup);
		return !(a && b);
	}
	
	private void logDifferences(String actualPayPeriod, String requiredPayPeriod) {
		logger.info("Currently available paygroup is [" + initPay.getPayGroup() + "], required [" + forPayGroup.getPayGroupName() + "]");
		logger.info("Currently available pay period is [" + actualPayPeriod + "], required [" + requiredPayPeriod + "]");
	}
	
	private boolean notAlreadyInitialised() {
		Button init = initPay.getInitialisePayroll(); 
		if(init != null) {
			return init.isAvailable();
		}else {
			return false;
		}
	}
//	public DialogOkCancel clickInitialisePayroll() {
//		Button init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
//		init.click();
//		return new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));
//	}
}
