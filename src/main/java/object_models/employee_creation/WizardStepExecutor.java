/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.StaleElementReferenceException;

import dto.Employee;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 */
public interface WizardStepExecutor {
	int getStepNumber();
	WizardStepExecutor writeValues(Employee emp) throws StaleElementReferenceException;
	WizardStepExecutor getNext();
}
