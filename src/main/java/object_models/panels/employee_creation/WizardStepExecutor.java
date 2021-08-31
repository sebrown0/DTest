/**
 * 
 */
package object_models.panels.employee_creation;

import java.util.Map;

import dto.Employee;
import object_models.element.ComboSelect;
import object_models.element.TextInput;

/**
 * @author Steve Brown
 *
 */
public interface WizardStepExecutor {
	int getStepNumber();
	void setTextBoxes(Map<String, TextInput> textBoxes);
	void setTextCombos(Map<String, ComboSelect> combos);
	WizardStepExecutor writeValues(Employee emp);
	WizardStepExecutor getNext();//WizardMove
}
