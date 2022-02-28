/**
 * 
 */
package controls.data_inserters.emp_lookup;

import control_builder.control_getters.single.ControlGetterEmployeeSelection;
import controls.Button;
import object_models.modal_forms.emp_selection.EmployeeSelection;
import object_models.panels.JsPanel;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class EmployeeLookup {
	private JsPanel panel;
	private ControlGetterEmployeeSelection empSelectionGetter;
	private EmployeeSelection empSelection;
	
	public EmployeeLookup(JsPanel panel) {
		this.panel = panel;
	}

	public void loadEmployee(String empCode) {
		openEmployeeList();
		selectEmployee();
	}
	
	private void openEmployeeList() {
		panel
			.getControlFromPanel("EmpLookup", "EmployeeList")
			.ifPresent(c -> { 
				((Button)c).click();
				empSelectionGetter = new ControlGetterEmployeeSelection(panel);
			});
	}
	
	private void selectEmployee() {
		if(empSelectionGetter != null) {
			System.out.println("slect"); // TODO - remove or log 	
			empSelection = (EmployeeSelection) empSelectionGetter.getControl();
			
		}
	}
}
