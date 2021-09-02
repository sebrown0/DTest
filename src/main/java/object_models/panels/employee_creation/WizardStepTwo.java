/**
 * 
 */
package object_models.panels.employee_creation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.PageMap;
import dto.Employee;
import object_models.date_picker.DatePickerPage;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class WizardStepTwo extends WizardStep {
	
	public WizardStepTwo(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
	}

	@Override
	public WizardStepExecutor writeValues(Employee emp) {
		DatePickerPage datePickerPage = new DatePickerPage(driver);
		
		pageMap.getCombo("Select a Gender").writeInput(emp.getGender().name());		
		
		//Write directly to the date text box and then confirm using the date picker.
		datePickerPage.writeDate("Date of Birth", pageMap.getTextBox("Date of Birth"), emp.getDateOfBirth());		
		//Use the date picker to 'move' the date.
		datePickerPage.getDatePicker("Date of Employment").setDate(emp.getDateOfEmployement());

		pageMap.getTextBox("Tax Number").writeInput(emp.getTaxNumber());
		pageMap.getTextBox("NI Number").writeInput(emp.getNiNumber());
//	pageMap.getCombo("Select a Bank").writeInput("REVOLUT");
		pageMap.getTextBox("IBAN Number").writeInput(emp.getIbanNumber());
		pageMap.getTextBox("Email Address").writeInput(emp.getEmailAddress());
		pageMap.getTextBox("Mobile Number").writeInput(emp.getMobileNumber());
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return new WizardStepThree(pageMap, driver, 3);	
	}
}
