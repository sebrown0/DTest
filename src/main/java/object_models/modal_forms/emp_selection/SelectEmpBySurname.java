/**
 * 
 */
package object_models.modal_forms.emp_selection;

import controls.TextSelect;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Execute the steps required in the 
 * employee selection from to select
 * an employee by surname.
 */
public class SelectEmpBySurname implements EmpSelectorBy {
	private String surname;
	private EmployeeSelection empSelection;
	
	public SelectEmpBySurname(EmployeeSelection empSelection, String surname) {
		this.empSelection = empSelection;
		this.surname = surname;
	}

	/*
	 * TODO - we are selecting the first row, 
	 * there could be multiple rows.
	 */
	@Override
	public void selectEmployee() {
		TextSelect srchThisComp = empSelection.getSearchThisCompany();
		srchThisComp.setText(surname);
		srchThisComp.hitEnter();
		empSelection.selectRow("1");		
	}	

}
