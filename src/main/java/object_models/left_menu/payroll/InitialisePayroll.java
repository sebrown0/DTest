package object_models.left_menu.payroll;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import controls.Button;
import enums.control_names.CommonControlNames;
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

	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";	
	
	public InitialisePayroll(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-DEFAULT", contextManager);

		buildMyControls();
	}		
	
	private void buildMyControls() {
		controlFactory = new ControlDataFactory(driver);
		var myControls = 
			List.of(
				controlFactory.buildTextSelect(
						CommonControlNames.COMPANY, 
						By.cssSelector("select[name='SelectComp']")).getControlData(),
				
				controlFactory.buildTextSelect(
						PayrollControlNames.PAY_GROUP, 
						By.cssSelector("select[name='SelectPayg']")).getControlData(),
				
				controlFactory.buildTextSelect(
						PayrollControlNames.PAY_PERIODS, 
						By.cssSelector("select[name='SelectPays']")).getControlData(),
				
				controlFactory
					.buildButton(
							PayrollControlNames.INIT_PAYROLL,
							By.cssSelector("div[type='button'][class='btn btn-warning']")).getControlData()
																						
		);			
		super.buildFormControls(myControls);				
	}
	
	// Actions
	public DialogOkCancel clickInitialisePayroll() {
		Button init = (Button) getControl(PayrollControlNames.INIT_PAYROLL).get();
		init.click();
		return new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));
	}
	
	@Override
	public void setMyContainers() {
		// None
	}

}