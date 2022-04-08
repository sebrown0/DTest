package object_models.modules.payroll.left_menu.payroll.initialise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import control_builder.control_data.ControlData;
import controls.button.Button;
import controls.with_text.TextSelect;
import enums.control_names.PayrollControlNames;
import exceptions.PayrollAlreadyInitialisedException;
import factories.ControlDataFactory;
import object_models.dialog.DialogOkCancel;
import object_models.forms.FormWithIFrame;
import object_models.modules.payroll.left_menu.payroll.PayrollElement;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add PayrollAlreadyInitialisedException to clickInitialisePayroll.
 * @since 1.0
 */
public class InitialisePayroll extends FormWithIFrame implements PayrollElement {	
	private ControlDataFactory controlFactory;
	private By byMyLocator;
	
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";	

	public InitialisePayroll(CoreData coreData) {
		super(coreData, PANEL_TITLE, "_iframex-DEFAULT");

		controlFactory = new ControlDataFactory(coreData);
		initialise();
	}
	
	// Initialise
	private void initialise() {
		setMyContainers();	
		buildMyControls();
	}
	
	@Override
	public void setMyContainers() {
		byMyLocator = By.cssSelector("div[class='modal show']");
	}
	
	private void buildMyControls() {		
		/*
		 * ControlTest is NULL.
		 * Shouldn't matter becuase it's for dynamic tests. 
		 */
		InitialisePayrollControls payrollControls = new InitialisePayrollControls(controlFactory, null);
		List<ControlData> myControls = payrollControls.getControls();		
		super.buildFormControls(myControls);				
	}
			
	// Actions		
	public DialogOkCancel clickInitialisePayroll() throws PayrollAlreadyInitialisedException {
		DialogOkCancel okCancel = null;
		Button init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
		if(init.click()) {
			okCancel = new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));	
		}else {
			WebElement initBtn = driver.findElement(By.cssSelector("div[type='button'][class='btn btn-danger']"));
			if(initBtn != null) {
				throw new PayrollAlreadyInitialisedException("");
			}
		}
		return okCancel;				
	}
	
	public String getPayPeriod() {
		TextSelect payPer = (TextSelect) getControl(PayrollControlNames.PAY_PERIODS).get();
		return payPer.getText();
	}
	
	public String getPayGroup() {
		TextSelect payGrp = (TextSelect) getControl(PayrollControlNames.PAY_GROUP).get();
		return payGrp.getText();
	}
	
	public Button getInitialisePayroll() {
		Button init = null;				
		if(getControl(PayrollControlNames.INIT_PAYROLL).isPresent()) {
			init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
		}
		return init;
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
	
}