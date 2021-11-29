package object_models.left_menu.payroll.initialise;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import control_builder.ControlData;
import controls.Button;
import enums.control_names.PayrollControlNames;
import factories.ControlDataFactory;
import object_models.dialog.DialogOkCancel;
import object_models.forms.FormWithIFrame;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class InitialisePayroll extends FormWithIFrame {	
	private ControlDataFactory controlFactory;
	private By byMyLocator;
	
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";	
	
	public InitialisePayroll(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-DEFAULT", contextManager);

		setMyContainers();
		controlFactory = new ControlDataFactory(driver);
		buildMyControls();
	}		
	
	private void buildMyControls() {		
		InitialisePayrollControls payrollControls = new InitialisePayrollControls(controlFactory);
		List<ControlData> myControls = payrollControls.getControls();		
		super.buildFormControls(myControls);				
	}
		
	// Actions
	public DialogOkCancel clickInitialisePayroll() {
		Button init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
		init.click();
		return new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));
	}
	
	public void closeForm() {
		super.switchToDefaultContent().deleteMyContextAndRevertToCallingContext();
		try {
			getCloseButton().click();	
		} catch (Exception e) {
			super.logger.error("Failed to close form [" + e.getMessage() + "]");
			System.out.println("->" + e.getMessage()); // TODO - remove or log 	
		}		
	}
	
	private WebElement getCloseButton() {
		WebElement form = driver.findElement(byMyLocator);
		WebElement close = form.findElement(By.cssSelector("button[class='btn btn-primary']"));
		
		return close;
	}
	
	@Override
	public void setMyContainers() {
		byMyLocator = By.cssSelector("div[class='modal show']");
	}

}