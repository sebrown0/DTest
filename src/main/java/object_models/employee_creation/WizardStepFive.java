/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.PageMap;
import dto.Employee;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class WizardStepFive extends WizardStep {
	
	public WizardStepFive(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
		
		super.loadAndWaitForStep(5);
	}

	@Override
	public WizardStepExecutor writeValues(Employee emp) {		
		pageMap.getTextBox("Annual Salary").writeInput(emp.getAnnualSalary().toPlainString());		
		pageMap.getTextBox("Hourly Rate").writeInput(emp.getHourlyRate().toPlainString());
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {		
		WebElement finishBtn = driver.findElement(By.cssSelector("a[href='#finish'"));		
		Jquery.goToElement(driver, finishBtn);
		return null;	
	}
}
