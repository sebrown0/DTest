/**
 * 
 */
package controls.data_inserters;

import java.util.Optional;

import control_builder.PageControl;
import controls.Button;
import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class EmployeeLookup {
	private PageControl pageControl;

	public EmployeeLookup(PageControl pageControl) {
		this.pageControl = pageControl;
	}
	
	public void loadEmployee(String empCode) {
		Optional<Control> empList = pageControl.getControl("EmployeeList");
		empList.ifPresentOrElse(
				list -> {
					Button btn = (Button) list;
					btn.click();
				}, 
				new Runnable() {			
					@Override
					public void run() {
						System.out.println("Could not get employee list"); // TODO - remove or log 	
					}
				}
		);
	}
	
}
