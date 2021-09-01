/**
 * 
 */
package object_models.panels.employee_creation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.PageMap;
import dto.Employee;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class WizardStepOne extends WizardStep {
	
	public WizardStepOne(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
	}

	@Override
	public WizardStepExecutor writeValues(Employee emp) {		
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
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return new WizardStepTwo(pageMap, driver, 2);	
	}
}
