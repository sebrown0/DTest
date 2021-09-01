/**
 * 
 */
package object_models.panels.employee_creation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dto.Employee;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class WizardStepOne extends WizardStep {	

	public WizardStepOne(WebDriver driver) {
		super(driver, 1);		
	}
	
	@Override
	public WizardStepExecutor writeValues(Employee emp) {
		textBoxes.get("Name").writeInput(emp.getFirstName());		
		textBoxes.get("Surname").writeInput(emp.getLastName());
		textBoxes.get("Identity Card Number").writeInput(emp.getIdCardNumber());
		textBoxes.get("Employee Code").writeInput(emp.getEmpCode());
		textBoxes.get("Address").writeInput(emp.getAddressOne());
		textBoxes.get("Street").writeInput(emp.getStreet());
		textBoxes.get("Post Code").writeInput(emp.getPostCode());
		combos.get("Select a Town").writeInput(emp.getTown());
		combos.get("Select a Country").writeInput(emp.getCountry());
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return new WizardStepTwo(driver);	
	}
}
