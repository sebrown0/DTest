/**
 * 
 */
package object_models.panels.employee_creation;

import dto.Employee;

/**
 * @author Steve Brown
 *
 */
public interface WizardStepExecutor {
	int getStepNumber();
	void loadControls();
//	void setTextBoxes(Map<String, InputWriter> textBoxes);	
//	void setTextCombos(Map<String, InputWriter> combos);
	WizardStepExecutor writeValues(Employee emp);
	WizardStepExecutor getNext();
}
