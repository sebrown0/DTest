/**
 * 
 */
package object_models.left_menu.payroll.initialise;

import org.apache.logging.log4j.Logger;

import entities.company.Company;
import entities.payroll.PayGroup;
import exceptions.PayrollAlreadyInitialisedException;
import object_models.dialog.DialogOkCancel;
import object_models.left_menu.common.LeftMenuLoadItem;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePageElement;
import object_models.pages.homepage.HomePagePayroll;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class PayrollInitialiser {
	private HomePagePayroll currentHomePage;
	private HomePageElement companyLoader;
	private Company forCompany;
	private PayGroup forPayGroup;
	private InitialisePayroll initPay;
	private CoreData coreData;
	private Logger logger;
	
	public PayrollInitialiser(HomePageElement companyLoader, Company forCompany, PayGroup forPayGroup, LeftMenuLoadItem leftMenu) {
		this.currentHomePage = (HomePagePayroll) companyLoader;
		this.companyLoader = companyLoader;
		this.forCompany = forCompany;
		this.forPayGroup = forPayGroup;
		this.coreData = (CoreData) companyLoader;
		this.logger = this.coreData.getLogger();
	}

	public HomePagePayroll initialisePayroll() {
		loadCompanyIfNecessary();
		if(openInitialisePayrollForm()) {
			if(formValuesEqualRequired()) {
				tryAndClickInitialise();				
			}	
			unloadForm();
		}		
		return currentHomePage;
	}
	
	private void loadCompanyIfNecessary() {
		currentHomePage = (HomePagePayroll) companyLoader.loadCompany(forCompany);
	}
	
	private boolean openInitialisePayrollForm() {		
		initPay = currentHomePage.openInitialisePayroll();
		return (initPay == null) ? false : true;
	}
	
	private boolean formValuesEqualRequired() {
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

	private void tryAndClickInitialise() {
		try {
			DialogOkCancel okCancel = initPay.clickInitialisePayroll();
			okCancel.clickCancel();
		} catch (PayrollAlreadyInitialisedException e) {
			logger.info("The payroll is already intitialised");
		}
	}

	private void unloadForm() {
		initPay.closeFormAndContext();
	}
}	
