/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import content.EmployeeContent;
import control_mapping.PageMap;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 */
public class WizardStepOne extends WizardStep {
	
	public WizardStepOne(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
		
		super.loadAndWaitForStep(1);
	}
	
	@Override
	public WizardStepExecutor writeValues(EmployeeContent emp) throws StaleElementReferenceException {
		pageMap.getTextBox("Name").writeInput(emp.getFirstName());		
		pageMap.getTextBox("Surname").writeInput(emp.getLastName());
		pageMap.getTextBox("Identity Card Number").writeInput(emp.getIdCardNumber());
		pageMap.getTextBox("Employee Code").writeInput(emp.getEmpCode());
		pageMap.getTextBox("Address").writeInput(emp.getAddressOne());
		pageMap.getTextBox("Street").writeInput(emp.getStreet());
		pageMap.getTextBox("Post Code").writeInput(emp.getPostCode());
		pageMap.getCombo("Select a Town").writeInput(emp.getTown());
		pageMap.getCombo("Select a Country").writeInput(emp.getCountry());
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		return new WizardStepTwo(pageMap, driver, 2);	
	}
}
