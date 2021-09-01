/**
 * 
 */
package object_models.panels.employee_creation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dto.Employee;
import loaders.DateSelectorLoader;
import object_models.date_picker.DatePickerPage;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class WizardStepTwo extends WizardStep {	

	public WizardStepTwo(WebDriver driver) {
		super(driver, 2);		
	}
	
	@Override
	public WizardStepExecutor writeValues(Employee emp) {
		DatePickerPage datePickerPage = new DatePickerPage(driver);
		
		combos.get("Select a Gender").writeInput(emp.getGender().name());		
		combos.get("Select a Bank").writeInput("REVOLUT");
		
		DateSelectorLoader loader = new DateSelectorLoader(driver);
		loader.printSelectors();
//		datePickerPage.getDatePicker().setDate("21 August 2000");
//		System.out.println("selected = " + datePickerPage.getSelectedDate());
	
		
//		datePickerPage.getDatePicker().setDate(emp.getDateOfBirth());
		
//		textBoxes.get("Date of Birth").writeInput("21/03/2000");
//		textBoxes.get("Date of Employment").writeInput("21/03/2000");
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return null;	
	}
}
