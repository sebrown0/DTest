/**
 * 
 */
package object_models.module_payroll.left_menu.payroll.initialise;

import java.util.List;

import org.openqa.selenium.By;

import control_builder.ControlData;
import enums.control_names.CommonControlNames;
import enums.control_names.PayrollControlNames;
import factories.ControlDataFactory;
import object_models.helpers.ElementControls;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * The controls that are in the body (iFrame) of the form.
 * Other controls, i.e. the close button are got dynamically.
 * 
 */
public class InitialisePayrollControls implements ElementControls {
	private ControlDataFactory controlFactory;

	public InitialisePayrollControls(ControlDataFactory controlFactory) {
		this.controlFactory = controlFactory;		
	}

	@Override
	public List<ControlData> getControls() {
		return List.of(
			controlFactory
				.buildTextSelect(
						CommonControlNames.COMPANY, 
						By.cssSelector("select[name='SelectComp']"))
				.getControlData(),
			
			controlFactory
				.buildTextSelect(
						PayrollControlNames.PAY_GROUP, 
						By.cssSelector("select[name='SelectPayg']"))
				.getControlData(),
			
			controlFactory
				.buildTextSelect(
						PayrollControlNames.PAY_PERIODS, 
						By.cssSelector("select[name='SelectPays']"))
				.getControlData(),
			
			controlFactory
				.buildButton(
						CommonControlNames.CLOSE,
						By.cssSelector("button[class='btn btn-primary']"))
				.getControlData(),		
				
			controlFactory
				.buildButton(
						PayrollControlNames.INIT_PAYROLL,
						By.cssSelector("div[type='button'][class='btn btn-warning']"))
				.getControlData()		
		);	
	}

}
