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
public class WizardStepFour extends WizardStep {
	
	public WizardStepFour(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
	}

	@Override
	public WizardStepExecutor writeValues(Employee emp) {		
		//DO NOTHING
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return new WizardStepFive(pageMap, driver, 5);	
	}
}
