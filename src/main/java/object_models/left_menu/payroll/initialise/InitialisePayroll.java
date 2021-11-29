package object_models.left_menu.payroll.initialise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import control_builder.ControlData;
import controls.Button;
import entities.Company;
import enums.control_names.PayrollControlNames;
import factories.ControlDataFactory;
import object_models.dialog.DialogOkCancel;
import object_models.forms.FormWithIFrame;
import object_models.pages.homepage.CompanyLoader;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class InitialisePayroll extends FormWithIFrame {	
	private ControlDataFactory controlFactory;
	private By byMyLocator;	
	private CoreData hp;	
//	private Company currentCompany;
	
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";	

	public InitialisePayroll(CoreData hp) {
		super(hp.getWebDriver(), PANEL_TITLE, "_iframex-DEFAULT", hp.getContextManager());

		controlFactory = new ControlDataFactory(driver);
		this.hp = hp;
		initialise();
	}
	
	// Initialise
	private void initialise() {
		setMyContainers();	
		buildMyControls();
//		setCurrentCompany();
	}
	
	@Override
	public void setMyContainers() {
		byMyLocator = By.cssSelector("div[class='modal show']");
	}
	
	private void buildMyControls() {		
		InitialisePayrollControls payrollControls = new InitialisePayrollControls(controlFactory);
		List<ControlData> myControls = payrollControls.getControls();		
		super.buildFormControls(myControls);				
	}
	
//	private void setCurrentCompany() {
//		currentCompany = getCompanyLoader().getCurrentCompany();
//	}
		
	// Actions
	
	
	
	
	public DialogOkCancel clickInitialisePayroll() {
		Button init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
		init.click();
		return new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));
	}
	
	public void closeForm() {
		super.switchToDefaultContent();
		closeMe();
		// KEEP THIS CONTEXT BUT SWITCH CONTEXT
	}	
	public void closeFormAndContext() {
		super.switchToDefaultContent().deleteMyContextAndRevertToCallingContext();
		closeMe();		
	}	
	private void closeMe() {
		try {
			getCloseButton().click();	
		} catch (Exception e) {
			super.logger.error("Failed to close form [" + e.getMessage() + "]");
		}
	}
	private WebElement getCloseButton() {
		WebElement form = driver.findElement(byMyLocator);
		WebElement close = form.findElement(By.cssSelector("button[class='btn btn-primary']"));		
		return close;
	}
	
	// Helpers
//	private CompanyLoader getCompanyLoader() {
//		return (CompanyLoader) hp;
//	}
	

}