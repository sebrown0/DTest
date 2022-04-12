/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import content.EmployeeContent;
import control_mapping.PageMap;
import object_models.helpers.Jquery;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 *
 */
public class WizardStepFive extends WizardStep {
	
	public WizardStepFive(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
		
		super.loadAndWaitForStep(5);
	}

	@Override
	public WizardStepExecutor writeValues(EmployeeContent emp) {		
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
