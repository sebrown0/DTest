/**
 * 
 */
package object_models.modal_forms.emp_selection;

import org.openqa.selenium.WebElement;

import controls.TextOut;
import controls.TextSelect;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class SelectEmpByCode implements EmpSelectorBy {
	private String code;
	private EmployeeSelection empSelection;
	
	public SelectEmpByCode(EmployeeSelection empSelection, String code) {
		this.empSelection = empSelection;
		this.code = code;
	}

	@Override
	public void selectEmployee() {
		System.out.println("selectEmployee"); // TODO - remove or log
		TextSelect srchThisComp = empSelection.getSearchThisCompany();
		srchThisComp.setText(code);
		srchThisComp.hitEnter();
		empSelection.clickRow("1");
		
//		WebElement result = empSelection.clickRow("1");
//		if(result != null) {
//			result.click();
//		}
	}
	
	private TextSelect getSearchThisCompany() {
		return empSelection.getSearchThisCompany(); 
	}

}
