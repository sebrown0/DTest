/**
 * 
 */
package object_models.employee_creation;

import org.openqa.selenium.StaleElementReferenceException;

import dto.Employee;

/**
 * @author Steve Brown
 *
 */
public interface WizardStepExecutor {
	int getStepNumber();
//	void loadControls();
//	void setTextBoxes(Map<String, InputWriter> textBoxes);	
//	void setTextCombos(Map<String, InputWriter> combos);
	WizardStepExecutor writeValues(Employee emp) throws StaleElementReferenceException;
	WizardStepExecutor getNext();
}
