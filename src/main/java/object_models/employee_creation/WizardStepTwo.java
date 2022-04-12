/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import content.EmployeeContent;
import control_mapping.PageMap;
import object_models.date_picker.DatePickerPage;
import object_models.helpers.Jquery;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 */
public class WizardStepTwo extends WizardStep {
	
	public WizardStepTwo(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);

		super.loadAndWaitForStep(2);
	}

	@Override
	public WizardStepExecutor writeValues(EmployeeContent emp) {
		DatePickerPage datePickerPage = new DatePickerPage(driver);
		
		//Write directly to the date text box and then confirm using the date picker.
		datePickerPage.writeDate("Date of Birth", pageMap.getTextBox("Date of Birth"), emp.getDateOfBirth());		
		//Use the date picker to 'move' the date.
		datePickerPage.getDatePicker("Date of Employment").setDate(emp.getDateOfEmployement());

//		pageMap.getCombo("Select a Gender").writeInput(emp.getGender().name());	
		pageMap.getCombo("Select a Gender").writeInput(emp.getGender());
		pageMap.getTextBox("Tax Number").writeInput(emp.getTaxNumber());
		pageMap.getTextBox("NI Number").writeInput(emp.getNiNumber());
		pageMap.getCombo("Select a Bank").writeInput(emp.getBank());
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