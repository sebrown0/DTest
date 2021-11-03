/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import controls.PageMap;
import dto.Employee;
import object_models.helpers.Jquery;

/**
 * @author Steve Brown
 *
 */
public class WizardStepThree extends WizardStep {
	
	public WizardStepThree(PageMap pageMap, WebDriver driver, int stepNumber) {
		super(pageMap, driver, stepNumber);
		
		WebElement btnStep = driver.findElement(By.id("wizard-t-2"));
		btnStep.click();
		waitUntilStepVisible();
	}

	private void waitUntilStepVisible() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-ST_3_LN_1_COL_1x-container")));
	}
	
	@Override
	public WizardStepExecutor writeValues(Employee emp) {
		WebElement e = driver.findElement(By.cssSelector("#wizard-p-2 > div:nth-child(3) > div:nth-child(2) > div > span > span.selection > span"));		
		e.sendKeys("Monthly Paygroup");
		e.sendKeys(Keys.ENTER);
		
////		pageMap.getCombo("Select a Tax Status").writeInput(emp.getTaxStatus().name());
////		pageMap.getCombo("Select an Employment Type").writeInput(emp.getEmploymentType().name());
		
//		System.out.println("->" + emp.getPayGroup()); // TODO - remove or log 	
//		pageMap.getCombo("Select a Paygroup").writeInput("Monthly Paygroup");
		
		
//		pageMap.getCombo("Select a Department").writeInput(emp.getDepartment());
//		pageMap.getCombo("Select a Schedule").writeInput(emp.getSchedule());
////		pageMap.getCombo("Full Time or Part Time ?").writeInput(emp.getEmploymentType().name());		
//		pageMap.getCombo("Special Part Timer ?").writeInput(emp.isSpecialPartTimer() == true ? "Yes" : "No");
//		pageMap.getCombo("Select a Grade").writeInput(emp.getGrade());
//		pageMap.getCombo("Select a Cost Center").writeInput(emp.getCostCentre());
		return this;
	}

	@Override
	public WizardStepExecutor getNext() {
		WebElement nextBtn = driver.findElement(super.byNext);		
		Jquery.goToElement(driver, nextBtn);
		return new WizardStepFour(pageMap, driver, 4);	
	}
}
