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
public class WizardStepTwo extends WizardStep {	

	public WizardStepTwo(WebDriver driver) {
		super(driver, 2);
		WizardStepLoader stepLoader = new WizardStepLoader(driver, this);		
		stepLoader.loadControls();		
	}
	
	@Override
	public WizardStepExecutor writeValues(Employee emp) {
//		System.out.println("->" + emp.getGender().name());
		combos.get("Select a Gender").writeInput(emp.getGender().name());		
		combos.get("Select a Bank").writeInput("REVOLUT");
//		textBoxes.get("Date of Birth").writeInput(emp.getDateOfBirth());
		textBoxes.get("Date of Birth").writeInput("21/03/2000");
		textBoxes.get("Date of Employment").writeInput("21/03/2000");
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return null;	
	}
}
