/**
 * 
 */
package controls.data_inserters;

import control_builder.control_getters.single.ControlGetterEmployeeSelection;
import controls.button.Button;
import controls.interfaces.ControlTest;
import object_models.modal_forms.emp_selection.EmployeeSelection;
import object_models.modal_forms.emp_selection.SelectEmpBySurname;
import object_models.modal_forms.emp_selection.SelectEmployee;
import object_models.panels.JsPanel;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Load an employee using the employee list button.
 * 
 * 1. Open employee list form by clicking the
 *    button to the side of employee code.
 * 2. Insert the code into search this company.
 * 3. Click enter.
 * 4. Select (click) the first row in the results.
 * 5. The form is unloaded and the employee
 *    should be loaded.
 *    
 *    TODO
 *    ----
 *    This only works for the current company and
 *    does not filter the results, 
 *    i.e. there could be more than one row returned
 *    for the name.
 *    
 */
public class EmployeeLookupByName implements TestDataInserter {
	private JsPanel panel;
	private EmployeeSelection empSelection;
	private String empName;
	
	public EmployeeLookupByName(ControlTest controlTest, String empName) {
		this.panel = (JsPanel) controlTest;
		this.empName = empName;
	}

	@Override
	public void insertData() {
		openEmployeeList();
		selectEmployee();
	}
	
	private void openEmployeeList() {
		panel
			.getControlFromPanel("EmpLookup", "EmployeeList")
			.ifPresent(c -> { 
				loadForm(((Button)c));
				setForm();
			});
	}
	
	private void loadForm(Button btn) {
		btn.click();
	}
	
	private void setForm() {
		empSelection = 
			(EmployeeSelection) 
				new ControlGetterEmployeeSelection(panel)
				.getControl();
	}
	
	private void selectEmployee() {
		if(empSelection != null) {
			SelectEmployee selEmp = empSelection;
			selEmp.UsingSelector(new SelectEmpBySurname(empSelection, empName));
		}
	}
	
}
