/**
 * 
 */
package controls.data_inserters.emp_lookup;

import controls.Button;
import object_models.controls.EmployeeSelection;
import object_models.panels.JsPanel;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class EmployeeLookup {
	private JsPanel panel;
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
			.getControlFromPanel("EmpLookupZX", "EmployeeListXX")
			.ifPresent(c -> { 
				((Button)c).click();
				empSelection = new EmployeeSelection(null, null);
			});
	}
	
	private void selectEmployee() {
		if(empSelection != null) {
			System.out.println("slect"); // TODO - remove or log 	
		}
	}
}
